job('Java Maven App DSL 0') {
    description('Java Maven App con DSL para el curso de Jenkins')
    scm {
        git('https://github.com/lordelfos/simple-java-maven-app.git', 'master') { node ->
            node / gitConfigName('lordelfos')
            node / gitConfigEmail('lordelfos@gmail.com')
        }
    }
    steps {
        maven {
          mavenInstallation('mavenjenkins')
          goals('-B -DskipTests clean package')
        }
        maven {
          mavenInstallation('mavenjenkins')
          goals('test')
        }
        shell('''
          echo "Entrega: Desplegando la aplicación" 
          java -jar "/var/jenkins_home/workspace/javaMaven0/target/my-app-1.0-SNAPSHOT.jar"
        ''')  
    }
    publishers {
        archiveArtifacts('target/*.jar')
        archiveJunit('target/surefire-reports/*.xml')

    }
}
