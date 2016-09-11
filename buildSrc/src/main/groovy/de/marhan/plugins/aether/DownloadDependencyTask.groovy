package de.marhan.plugins.aether

import com.jcabi.aether.Aether
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.sonatype.aether.artifact.Artifact
import org.sonatype.aether.repository.RemoteRepository
import org.sonatype.aether.util.artifact.DefaultArtifact

class DownloadDependencyTask extends DefaultTask {

    @Input
    String repositoryId;

    @Input
    String repositoryType;

    @Input
    String repositoryUrl;

    @Input
    String artifactCoords;

    @Input
    String destinationFolder

    DownloadDependencyTask() {
        group = 'marhan'
        description = 'Loads dependencies from repositories'
    }

    @TaskAction
    void start() {
        logger.info("load dependency")

        File localDestination = new File(destinationFolder);
        def artifact = new DefaultArtifact(artifactCoords)
        Collection<RemoteRepository> remotes = Arrays.asList(new RemoteRepository(repositoryId, repositoryType, repositoryUrl));
        def aether = new Aether(remotes, localDestination)
        Collection<Artifact> deps = aether.resolve(artifact, "runtime");

        logger.info("Got the dependencies")
    }
}