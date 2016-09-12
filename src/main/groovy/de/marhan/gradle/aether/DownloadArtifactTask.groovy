package de.marhan.gradle.aether

import com.jcabi.aether.Aether
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.sonatype.aether.artifact.Artifact
import org.sonatype.aether.repository.RemoteRepository
import org.sonatype.aether.util.artifact.DefaultArtifact

class DownloadArtifactTask extends DefaultTask {

    @Input
    String repositoryId;

    @Input
    String repositoryType;

    @Input
    String repositoryUrl;

    @Input
    String artifactCoords;

    @Input
    String destinationFolder;

    DownloadArtifactTask() {
        group = 'de.marhan'
        description = 'Gets an artifact from repository.'
    }

    @TaskAction
    void start() {
        withExceptionHandling {

            File localDestination = new File(destinationFolder);
            def artifact = new DefaultArtifact(artifactCoords)
            Collection<RemoteRepository> remotes = Arrays.asList(new RemoteRepository(repositoryId, repositoryType, repositoryUrl));
            def aether = new Aether(remotes, localDestination)
            Collection<Artifact> deps = aether.resolve(artifact, "runtime");

        }
    }

    private void withExceptionHandling(Closure c) {
        try {
            c()
        }
        catch (Exception e) {
            logger.error "Failed to execute DownloadArtifact task", e
            throw new GradleException(e.message)
        }
    }
}

