pipelineJob('cxp-core-webapp-build-PR-Triggered') {
    description 'Pipeline Job to build cxp-core-webapp of PRs branch through PR trigger'
    
    configure {
        it / 'properties' / 'com.coravy.hudson.plugins.github.GithubProjectProperty' {
          'projectUrl'('https://github.com/ncr-swt-banking/cxp-core-webapp/')
        }
    }

    parameters {
        stringParam('branch', '$ghprbSourceBranch', 'PRs branches')
    }


    triggers {
        githubPullRequest {
            orgWhitelist('ncr-swt-banking')
            cron('H/5 * * * *')         //This is the default command 
            useGitHubHooks()
        }
    }

    definition {
        def pipelineScript = readFileFromWorkspace('pipeline_seed.txt')
        cps {
            script(pipelineScript)
            sandbox()
        }
    }
}
