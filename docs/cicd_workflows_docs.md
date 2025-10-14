# CI/CD Workflows - Documentation

## Overview

NutriMC uses a GitFlow-inspired CI/CD strategy with multiple workflows for different branches and environments.

---

## Branching Strategy

```
main (Production)
  ↑
  └── dev (Testing)
       ↑
       └── feature/* (Development)
```

**Branch Purposes:**
- `main`: Production-ready code, deployed to AWS production
- `dev`: Integration/testing environment, deployed to AWS testing
- `feature/*`: Individual feature development

---

## Workflow Files

### 1. feature-ci.yml - Feature Branch CI
**Location:** `.github/workflows/feature-ci.yml`

**Triggers:**
- Pushes to any `feature/**` branch

**Purpose:** Fast feedback for feature development

**Jobs:**
1. **quick-checks**:
   - Compile code
   - Run unit tests only (not integration tests)

**Runtime:** ~1-2 minutes (fastest)

**Why separate workflow?**
- Developers get faster feedback
- Doesn't run expensive integration tests
- Encourages frequent commits

---

### 2. dev-ci.yml - Dev Environment CI/CD
**Location:** `.github/workflows/dev-ci.yml`

**Triggers:**
- Pull requests to `dev`
- Pushes to `dev`

**Purpose:** Full testing and deployment to testing environment

**Jobs:**
1. **full-test-suite**:
   - Run `mvn clean verify` (unit + integration tests)
   - More comprehensive than feature CI

2. **deploy-to-testing** (only on push, not PR):
   - Deploys to AWS testing environment
   - Only runs after tests pass
   - Only runs on actual merge, not PR

**Runtime:** ~3-5 minutes

**Deployment trigger:**
- PR to dev: Tests run, no deployment
- Merge to dev: Tests run + automatic deployment

---

### 3. main-ci.yml - Production CI
**Location:** `.github/workflows/main-ci.yml`

**Triggers:**
- Pull requests to `main`

**Purpose:** Final validation before production

**Jobs:**
1. **final-checks**:
   - Run full test suite
   - Security scan placeholder
   - Build production artifact

**Runtime:** ~3-5 minutes

**Does NOT deploy** - deployment is manual via separate workflow

---

### 4. deploy-production.yml - Production Deployment
**Location:** `.github/workflows/deploy-production.yml`

**Triggers:**
- Manual workflow_dispatch (button click in GitHub Actions)

**Purpose:** Controlled production deployment

**Jobs:**
1. **validate-confirmation**:
   - Requires typing "DEPLOY" to confirm
   - Prevents accidental deployments

2. **deploy**:
   - Deploys to AWS production environment
   - Only runs if confirmation matches

**Runtime:** ~5-10 minutes (when implemented)

**How to trigger:**
1. Go to Actions tab
2. Select "Deploy to Production"
3. Click "Run workflow"
4. Type "DEPLOY" in confirmation field
5. Click "Run workflow" button

---

## Workflow Comparison

| Workflow | Triggers | Tests | Deploy | Speed | Purpose |
|----------|----------|-------|--------|-------|---------|
| feature-ci.yml | Push to feature/* | Unit only | No | ⚡ Fast | Quick feedback |
| dev-ci.yml | PR/Push to dev | Full suite | Testing | 🔄 Medium | Integration testing |
| main-ci.yml | PR to main | Full suite | No | 🔄 Medium | Pre-production validation |
| deploy-production.yml | Manual | No | Production | 🐢 Slow | Controlled deployment |

---

## Development Workflow

### Creating a New Feature

```bash
# 1. Start from dev
git checkout dev
git pull origin dev

# 2. Create feature branch
git checkout -b feature/add-meal-plan

# 3. Write code and commit
git add .
git commit -m "feat: add meal plan entity"
git push origin feature/add-meal-plan

# ✅ feature-ci.yml runs (compile + unit tests)

# 4. Create PR to dev
# On GitHub: feature/add-meal-plan → dev

# ✅ dev-ci.yml runs (full test suite)

# 5. Merge to dev after approval
# ✅ dev-ci.yml runs again + deploys to testing

# 6. Test in testing environment
# Manual testing on AWS testing environment

# 7. Create PR to main
# On GitHub: dev → main

# ✅ main-ci.yml runs (final validation)

# 8. Merge to main after approval
# ✅ Code is in main but NOT deployed

# 9. Deploy to production
# Go to Actions → Deploy to Production → Run workflow
# Type "DEPLOY" → Deploy

# ✅ deploy-production.yml runs
```

---

## Workflow Consistency

All workflows follow these standards:

### Naming Conventions
- **Jobs**: lowercase with hyphens (e.g., `full-test-suite`)
- **Steps**: Sentence case (e.g., "Run all tests")

### Common Steps Pattern
```yaml
steps:
  - name: Checkout code
    uses: actions/checkout@v4

  - name: Set up JDK 21
    uses: actions/setup-java@v4
    with:
      java-version: '21'
      distribution: 'temurin'

  - name: Cache Maven dependencies
    uses: actions/cache@v3
    with:
      path: ~/.m2
      key: ${{ runner.os }}-m2-${{ hashFiles('**/pom.xml') }}
      restore-keys: ${{ runner.os }}-m2
```

### Test Commands
- Feature CI: `mvn test` (unit tests only)
- Dev CI: `mvn clean verify` (full suite)
- Main CI: `mvn clean verify` (full suite)

---

## Future Enhancements

### Days 8-10 (Infrastructure)
- [ ] Implement Terraform deployment in `deploy-to-testing`
- [ ] Implement Ansible deployment in `deploy-production.yml`
- [ ] Add AWS credentials management

### Days 11-12 (Testing)
- [ ] Add E2E tests to `dev-ci.yml`
- [ ] Add integration tests to all workflows
- [ ] Add security scanning to `main-ci.yml`
- [ ] Fix test report generation in `ci.yml`

### Future Improvements
- [ ] Add code coverage reporting
- [ ] Add SonarQube integration
- [ ] Add SpotBugs to `code-quality` job
- [ ] Add Spotless formatting checks
- [ ] Add Docker image building
- [ ] Add performance testing

---

## Troubleshooting

### Workflow Not Running
**Problem:** Workflow doesn't trigger
**Check:**
1. Workflow file exists on the base branch (not just feature branch)
2. Trigger conditions match (branch name, event type)
3. YAML syntax is valid

### Tests Failing
**Problem:** Tests pass locally but fail in CI
**Check:**
1. Different Java version
2. Missing environment variables
3. Database configuration differences
4. Timezone issues

### Cache Not Working
**Problem:** Maven downloads dependencies every time
**Check:**
1. Cache key matches across runs
2. `pom.xml` hasn't changed
3. Cache size limits not exceeded

### Deployment Fails
**Problem:** Deployment step fails
**Check:**
1. AWS credentials configured
2. Terraform/Ansible files exist
3. Target environment accessible

---

## Branch Protection Rules

### Recommended Settings

**main branch:**
- ✅ Require pull request before merging
- ✅ Require status checks to pass (main-ci.yml)
- ✅ Require conversation resolution
- ❌ Do not allow bypassing (unless solo project)

**dev branch:**
- ✅ Require pull request before merging
- ✅ Require status checks to pass (dev-ci.yml)
- ⚠️ Allow force pushes (only for fixing issues)

**feature/* branches:**
- ❌ No restrictions (fast development)

---

## Monitoring CI/CD

### Where to Check Status

**1. Pull Request Page:**
- Shows all required checks
- Click "Details" to see logs

**2. Actions Tab:**
- See all workflow runs
- Filter by workflow, branch, status
- View detailed logs

**3. Commit Status:**
- Green checkmark = passed
- Red X = failed
- Yellow dot = running

### Common Status Messages

✅ **"All checks have passed"**
- Safe to merge

❌ **"Some checks were not successful"**
- Click "Details" to see which failed
- Fix issue and push again

⏳ **"Checks are running"**
- Wait for completion
- Usually 2-5 minutes

---

## Cost Considerations

**GitHub Actions Minutes:**
- Free tier: 2,000 minutes/month (public repos unlimited)
- Our workflows: ~5 minutes per run
- Estimated usage: ~100 runs/month = 500 minutes

**AWS Costs:**
- Testing environment: ~$10-15/month
- Production environment: ~$20-30/month
- Total: ~$30-45/month

---

## Related Documentation

- [GitHub Actions Documentation](https://docs.github.com/en/actions)
- [GitFlow Workflow](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow)
- [Maven Lifecycle](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)

---

## Status
✅ **Implemented** - Basic CI/CD structure in place
🚧 **In Progress** - Deployment automation (Days 9-10)
📋 **Planned** - Advanced testing and monitoring (Days 11-12)