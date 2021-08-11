def call(Map params = [:] ) {
    def args = []
    args << params
    
    pipeline {
        agent {
            label "${args.SLAVE_LABEL}"
        }
    
        environment {
            COMPONENT ="${args.COMPONENT}"
            PROJECT_NAME = "${args.PROJECT_NAME}"
            SLAVE_LABEL = "${args.SLAVE_LABEL}"
            APP_TYPE    = "${args.APP_TYPE}"
        }
        stages {
            stage('Build code & install dependencies') {
                steps {
                  sh '''
                    docker build -t local .
                  '''
                }

            }

        }
    }
}