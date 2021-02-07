properties([
    parameters([
        string(defaultValue: '', description: 'Provide node IP', name: 'node', trim: true)
        ])
    ])

node{
    stage("Pull Repo"){
        git url: 'https://github.com/azizbekmol/ansible-Flaskex.git'
    }
    stage("Install Prerequisites"){
        ansiblePlaybook become: true, colorized: true, credentialsId: 'jenkins-master', disableHostKeyChecking: true, inventory: "${params.node},", playbook: 'prerequisites.yml'
    }
    withEnv(['FLASKEX_REPO=https://github.com/ikambarov/Flaskex.git', 'FLASKEX_BRANCH=master']) {
        stage("pull Repo"){
            ansiblePlaybook become: true, colorized: true, credentialsId: 'jenkins-master', disableHostKeyChecking: true, inventory: "${params.node},", playbook: 'pull_repo.yml'
            }
        }

    stage("Install Python"){
        ansiblePlaybook become: true, colorized: true, credentialsId: 'jenkins-master', disableHostKeyChecking: true, inventory: "${params.node},", playbook: 'install_python.yml'
    }
    stage("Start app"){
        ansiblePlaybook become: true, colorized: true, credentialsId: 'jenkins-master', disableHostKeyChecking: true, inventory: "${params.node},", playbook: 'start_app.yml'
    }   
}