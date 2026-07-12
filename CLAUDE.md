# Ascended Quark - Agent Directives

NeoForge mod for Minecraft `${mc_version}` developed by RazorDevs. Automated agents MUST strictly adhere to these rules before contributing.

---

## 1. Environment & Context
* **Target:** Use NeoForge standards. Do not use legacy Forge or Fabric APIs/registries.
* **Configuration:** Read `build.properties` first. Variables like `${mc_version}` expand dynamically from it.
* **Architecture:** Maintain existing codebase structures (client/server separation, packets, registry handling).

---

## 2. Identity & Tracking
All contributions must be explicitly signed. Unidentified work will be rejected.

### Code Comments
Prefix all code comments with your agent name in block capitals wrapped in double dashes. No unsigned comments allowed.
* **Correct:** `/* --CODEX-- FIX: Prevent NPE on null capability */`
* **Incorrect:** `/* FIX: Fixed bug */`

### Branching & Commits
* **Branch Naming:** `agent/[AgentName]/[short-description]` (e.g., `agent/codex/fix-registry`).
* **Commit Messages:** Prefix with tag: `[AgentName] Closes #123 - Description`.

---

## 3. Scope Control & Micro-Changes
* **Single Focus:** Work only on the assigned issue. Do not fix unrelated bugs, modify random files, or update dependencies.
* **Size Limits:** Max **5 files** or **150 changed lines** per Pull Request.
* **Incremental Refactors:** Large rewrites are forbidden. Break major changes into micro-milestones requiring human approval.

---

## 4. Pull Request & Quality Standards
Low-quality or unverified PRs will be automatically rejected.

* **PR Title:** Format as `[AgentName] Short Description`.
* **Code Quality:** Must compile via `./gradlew compileJava`. Catch specific exceptions, not generic `Exception`.
* **PR Description Template:** Every PR must include this exact block filled out:
  ```markdown
  ### Agent Info
  * **Name:** [Agent Name]
  * **Objective:** [Brief fix summary]
  * **Files Impacted:** [List files]

  ### Verification Checklist
  - [ ] Code compiles via `./gradlew compileJava`
  - [ ] No regressions introduced to NeoForge registries
  - [ ] All comments are self-identified properly
  ```
---

## 5. Safety & Security Guardrails

* **No Unauthorized Tools:** Do not add external dependencies, gradle plugins, or execute unapproved shell commands.
* **No Mass Deletion:** Do not delete assets, localization files (`.json`), or configuration files without human confirmation.
* **Security:** Never log, expose, or hardcode environment variables, tokens, or system paths.
