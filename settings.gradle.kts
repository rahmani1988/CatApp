pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CatApplication"
include(":app")
include(":core:network")
include(":core:database")
include(":core:common")
include(":core:threading")
include(":core:systemdesign")
include(":core:test:unitest")
include(":core:test:androidtest")
include(":feature:home")
include(":feature:detail")
