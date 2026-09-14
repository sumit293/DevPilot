# Upgrade Plan: backend (20260914115617)

- **Generated**: 2026-09-14
- **HEAD Branch**: N/A
- **HEAD Commit ID**: N/A

## Available Tools

**JDKs**
- JDK 21: not available (baseline will be skipped)
- JDK 25: **<TO_BE_INSTALLED>** (required by upgrade and final validation)

**Build Tools**
- Maven Wrapper: 3.9.16 (available at `backend/mvnw.cmd`)

Version control is unavailable because the workspace is not a Git repository; changes will remain uncommitted in the working directory.

## Guidelines

> Note: You can add any specific guidelines or constraints for the upgrade process here if needed, bullet points are preferred.

- Upgrade the Java runtime to the latest LTS version.
- Execute the upgrade automatically after plan generation.

## Options

- Working branch: N/A (version control unavailable)
- Run tests before and after the upgrade: true

## Upgrade Goals

- Java 25

## Technology Stack

| Technology/Dependency | Current | Min Compatible Version | Why Incompatible |
| --------------------- | ------- | ---------------------- | ---------------- |
| Java | 21 | 25 | User requested latest LTS runtime |
| Spring Boot | 4.1.1 | 4.1.1 | Already compatible with the requested Java target |
| Spring AI | 2.0.1 | 2.0.1 | No upgrade required for the Java-only goal |
| Maven Wrapper | 3.9.16 | 3.9.0 | Current wrapper is compatible with Java 25 |
| maven-compiler-plugin | Spring Boot managed | 3.11.0 | Current managed version is expected to support Java 25; no explicit override found |

## Derived Upgrades

- Set the Maven compiler property `java.version` to `25` so Spring Boot dependency management and the compiler target the requested runtime.
- Install JDK 25 because it is required to compile and run the application after the upgrade.
- No Kotlin upgrade is required because the project contains no Kotlin sources or Kotlin build configuration.
- No Maven wrapper upgrade is required because Maven Wrapper 3.9.16 is already current and compatible.

## Impact Analysis

### Dependency Changes

| File | Dependency | Current | Action | Target | Reason |
|------|------------|---------|--------|--------|--------|
| `backend/pom.xml` | `java.version` | 21 | upgrade | 25 | Sets the project source, target, and runtime compatibility level |

### Source Code Changes

No Java source changes are expected. The application uses standard Spring Boot startup APIs and has no JDK-internal imports or reflective access identified in the available source files.

### Configuration Changes

No application configuration changes are required for a Java runtime-only upgrade.

### CI/CD Changes

No CI/CD files were found in the backend project requiring a Java version update.

### Risks & Warnings

- **Missing baseline JDK 21**: The current JDK could not be located, so pre-upgrade compilation and test results cannot be recorded. **Mitigation**: Run the complete target-JDK build and test suite after installing JDK 25.
- **Java 25 runtime compatibility**: Dependencies may expose compile or runtime issues only after the target JDK is active. **Mitigation**: Run `mvnw.cmd clean test-compile` and `mvnw.cmd clean test` with JDK 25, then resolve all failures.
- **CVE status may change with the resolved dependency graph**: **Mitigation**: Extract direct dependencies and run the Java CVE scan after the POM change; apply only necessary patched versions and re-verify.

## Upgrade Steps

- Step 1: Setup Environment
  - **Rationale**: Install the required Java 25 runtime before modifying or validating the project.
  - **Changes to Make**: Install JDK 25 and use the Maven Wrapper.
  - **Verification**: List JDKs and confirm a Java 25 installation path exists.

- Step 2: Setup Baseline
  - **Rationale**: Establish pre-upgrade compilation and test results when the base JDK is available.
  - **Changes to Make**: None; the step is skipped because JDK 21 is unavailable.
  - **Verification**: Skipped with documented reason.

- Step 3: Upgrade Java Target
  - **Rationale**: Apply the requested Java 25 target to the Maven project.
  - **Changes to Make**: Apply the Dependency Changes entry for `backend/pom.xml`.
  - **Verification**: `backend\mvnw.cmd clean test-compile -q` with JDK 25; main and test compilation must succeed.

- Step 4: CVE Validation and Fix
  - **Rationale**: Confirm that the upgraded dependency graph has no known dependency vulnerabilities requiring remediation.
  - **Changes to Make**: Scan direct dependencies; upgrade only vulnerable dependency versions if reported.
  - **Verification**: Compile after any fixes and repeat the CVE scan until all actionable findings are resolved.

- Step 5: Final Validation
  - **Rationale**: Confirm the Java 25 goal and the 100% test success criterion.
  - **Changes to Make**: Resolve all compilation and test failures found under JDK 25.
  - **Verification**: `backend\mvnw.cmd clean test -q` with JDK 25; compilation succeeds and all tests pass.
