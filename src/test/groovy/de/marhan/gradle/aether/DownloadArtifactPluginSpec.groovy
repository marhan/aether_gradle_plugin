package de.marhan.gradle.aether

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class DownloadArtifactPluginSpec extends Specification {

    static final TASK_NAME = 'downloadArtifact'
    Project project

    def setup() {
        project = ProjectBuilder.builder().build()
    }

    def "Applies plugin and sets extension values"() {
        expect:
        project.tasks.findByName(TASK_NAME) == null

        when:
        project.apply plugin: 'downloadArtifact'

        project.downloadArtifact {
            repositoryId = 'maven-central'
            repositoryType = 'default'
            repositoryUrl = 'http://repo1.maven.org/maven2/'
            artifactCoords = 'junit:junit-dep:4.10'
            destinationFolder = 'build/'
        }

        then:
        project.extensions.findByName(DownloadArtifactPlugin.EXTENSION_NAME) != null

        Task task = project.tasks.findByName(TASK_NAME)
        task != null

        task.description == 'Gets an artifact from repository.'
        task.group == 'de.marhan'

        task.repositoryId == 'maven-central'
        task.repositoryType == 'default'
        task.repositoryUrl == 'http://repo1.maven.org/maven2/'
        task.artifactCoords == 'junit:junit-dep:4.10'
        task.destinationFolder == 'build/'
    }
}