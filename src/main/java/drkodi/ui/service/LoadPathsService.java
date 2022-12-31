package drkodi.ui.service;

import drkodi.MovieEntries;
import drkodi.RenamingPathEntries;
import drkodi.SearchResultDtoMapper;
import drkodi.PrototypeService;
import drkodi.config.AppConfig;
import drkodi.data.SearchResultToMovieMapper;
import drkodi.data.movie.Movie;
import drkodi.normalization.FolderNameWarningNormalizer;
import drkodi.normalization.MovieTitleSearchNormalizer;
import drkodi.normalization.MovieTitleWriteNormalizer;
import drkodi.themoviedb.MovieDbSearcher;
import drrename.commons.RenamingPath;
import javafx.concurrent.Task;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;

/**
 * The 'initial' service. It loads a given set of {@link Path}, converts all child items to instances of
 * {@link drrename.commons.RenamingPath} and adds those to given instance of {@link RenamingPathEntries}.
 */
@Setter
@Component
public class LoadPathsService extends PrototypeService<Void> {

    static final String LOADING_FILES = "mainview.status.loading_files";

    private Collection<Path> files;

    public LoadPathsService(AppConfig appConfig, ResourceBundle resourceBundle, MovieEntries movieEntries, Executor executor, SearchResultToMovieMapper searchResultToMovieMapper, SearchResultDtoMapper mapper, MovieDbSearcher movieDbSearcher, MovieTitleSearchNormalizer movieTitleSearchNormalizer, MovieTitleWriteNormalizer movieTitleWriteNormalizer, FolderNameWarningNormalizer folderNameWarningNormalizer, Collection<Path> files) {
        super(appConfig, resourceBundle, movieEntries, searchResultToMovieMapper, mapper, movieDbSearcher, movieTitleSearchNormalizer, movieTitleWriteNormalizer, folderNameWarningNormalizer);
        this.files = files;
    }

    @Override
    protected Task<Void> createTask() {
        // If 'files' is one entry only, and it's a directory, use ListDirectoryTask, otherwise use ListFilesTask.
        if (files != null && files.size() == 1 && Files.isDirectory(files.iterator().next())) {
            return new ListDirectoryTask(getAppConfig(), getResourceBundle(),movieEntries,getExecutor(), searchResultToMovieMapper,mapper,movieDbSearcher,movieTitleSearchNormalizer,movieTitleWriteNormalizer,folderNameWarningNormalizer, files.iterator().next());
        }
        return new ListFilesTask(getAppConfig(), getResourceBundle(),movieEntries,getExecutor(), searchResultToMovieMapper,mapper,movieDbSearcher,movieTitleSearchNormalizer,movieTitleWriteNormalizer,folderNameWarningNormalizer, files);
    }



    @Override
    public String toString() {
        return getClass().getSimpleName() + " file cnt: " + (files == null ? 0 : files.size());
    }


}

