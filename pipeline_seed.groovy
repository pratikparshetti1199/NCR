pipelineJob('ESS/core_cxp-core-webapp-build-PR-Triggered') {
    description 'Pipeline Job to build cxp-core-webapp of PRs branch through PR trigger'
    }
  
    configure {
		it / 'properties' / 'com.coravy.hudson.plugins.github.GithubProjectProperty' {
			'projectUrl'('https://github.com/ncr-swt-banking/cxp-core-webapp/')
			displayName()
		}
	}

    parameters {
        stringParam('branch', '$ghprbSourceBranch', 'PRs branches')
//        booleanParam('forceDeploy', false, 'force a deploy, even if not coming from master/develop')
    }

/*    configure {
        project -> (project / 'authToken').setValue('cxpcorewebappbuildtoken')
    }
*/
    definition {
        def pipelineScript = 'https://github.com/pratikparshetti1199/NCR.git/pipeline_seed.txt'
        cps {
            script(pipelineScript)
            sandbox()
        }
    }
}
