# Workflow Convention -> Feature Branch Worflow
https://www.atlassian.com/git/tutorials/comparing-workflows/feature-branch-workflow

Do not change POM version for this student project-> allways 1.0.0-SNAPSHOT 

integration-branch (one branch per sprint): from main -> back to main         
feature-branch: (feature/*) -> from integration  -> back to integration 
hotfix-branch: (hotfix/*) -> from main -> back to main
main-branch: merge triggers CI tests 
release-branch: new POM versionmerge triggers jr file upload
