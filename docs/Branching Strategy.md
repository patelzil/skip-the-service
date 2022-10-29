# Branching Strategy

We will be using the Git branching strategy. In this strategy we will have a `master` branch which will be supported by our main development branch called `dev`.  

Features will be worked in branches taken of the `dev` branch and when completed merged back to the `dev`. For releases we branch off of the `dev` branch to the release branch and then further to the `hotfix` branch if required before pushing all the way to the `master` branch once everything is checked and double checked.
