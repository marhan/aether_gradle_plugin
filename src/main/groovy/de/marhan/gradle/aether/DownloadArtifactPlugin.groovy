package de.marhan.gradle.aether

import org.gradle.api.Plugin
import org.gradle.api.Project

public class DownloadArtifactPlugin implements Plugin<Project> {

    final static String EXTENSION_NAME = "downloadArtifact"

    @Override
    void apply(Project project) {
        project.extensions.create(EXTENSION_NAME, DownloadArtifactPluginExtension)
        addTasks(project)
    }

    void addTasks(Project project) {

        project.tasks.withType(DownloadArtifactTask) {
            def extension = project.extensions.findByName(EXTENSION_NAME)
        }

        project.task('downloadArtifact', type: DownloadArtifactTask) {
            conventionMapping.repositoryId = { getRepositoryId(project) }
            conventionMapping.repositoryType = { getRepositoryType(project) }
            conventionMapping.repositoryUrl = { getRepositoryUrl(project) }
            conventionMapping.artifactCoords = { getArtifactCoords(project) }
            conventionMapping.destinationFolder = { getDestinationFolder(project) }
        }
    }

    private String getRepositoryId(Project project) {
        project.hasProperty('repositoryId') ?
                project.repositoryId : project.extensions.findByName(EXTENSION_NAME).repositoryId
    }

    private String getRepositoryType(Project project) {
        project.hasProperty('repositoryType') ?
                project.repositoryType : project.extensions.findByName(EXTENSION_NAME).repositoryType
    }

    private String getRepositoryUrl(Project project) {
        project.hasProperty('repositoryUrl') ?
                project.repositoryUrl : project.extensions.findByName(EXTENSION_NAME).repositoryUrl
    }

    private String getArtifactCoords(Project project) {
        project.hasProperty('artifactCoords') ?
                project.artifactCoords : project.extensions.findByName(EXTENSION_NAME).artifactCoords
    }

    private String getDestinationFolder(Project project) {
        project.hasProperty('destinationFolder') ?
                project.destinationFolder : project.extensions.findByName(EXTENSION_NAME).destinationFolder
    }
}
