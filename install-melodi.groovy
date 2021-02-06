properties([
    parameters([
        string(defaultValue: '', description: 'Provide node IP', name: 'node', trim: true)
        ])
    ])

node {
<<<<<<< HEAD
    stage("Pull Repo"){
        sh "rm -rf ansible-melodi && git clone https://github.com/azizbekmol/ansible-melodi.git"
    }
=======
    stage("PullRepo"){
        sh "rm -rf ansible-melodi && git clone https://github.com/azizbekmol/ansible-melodi.git"
    }

>>>>>>> d906624c29c976ef16b73e98cf8e021bb009854c
    withCredentials([sshUserPrivateKey(credentialsId: 'jenkins-master', keyFileVariable: 'SSHKEY', passphraseVariable: '', usernameVariable: 'SSHUSERNAME')]) {
        stage("Install Melodi"){
            sh """
                export ANSIBLE_HOST_KEY_CHECKING=False
                ansible-playbook -i "${params.node}," --private-key $SSHKEY ansible-melodi/main.yml -b -u $SSHUSERNAME
            """
<<<<<<< HEAD
        }
    }
}
=======
        }    
    }
}
>>>>>>> d906624c29c976ef16b73e98cf8e021bb009854c
