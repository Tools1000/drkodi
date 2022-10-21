package drrename.strategy;

import drrename.RenameUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public abstract class RenamingStrategyProto implements RenamingStrategy {

    private final static Pattern pattern = Pattern.compile(".*_copy(\\d*)$");

    private final ResourceBundle resourceBundle;

    private String replacementStringFrom = "";

    private String replacementStringTo = "";

    public RenamingStrategyProto(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    protected ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    @Override
    public String getName() {
        return String.format(String.format(getResourceBundle().getString(getNameId())));
    }

    @Override
    public String getHelpText() {
        return String.format(String.format(getResourceBundle().getString(getHelpTextId())));
    }

    protected abstract String getNameId();

    protected abstract String getHelpTextId();

    /**
     * Performs the rename. Does not override existing files, but creates numbered suffixes for file names that exist
     * already.
     *
     * @param file    the file to rename
     * @param nameNew the new file name
     * @return
     * @throws IOException
     */
    protected Path doRename(final Path file, String nameNew) throws IOException {

        int fileNameCounter = 1;
        try {
            final String nameOld = getNameOld(file);
            if (nameOld.equals(nameNew)) {
                log.debug("Skipping '" + nameOld + "'");
                return file;
            }
            log.debug("Renaming" + IOUtils.LINE_SEPARATOR + "old:\t" + nameOld + IOUtils.LINE_SEPARATOR + "new:\t" + nameNew);
            return RenameUtil.rename(file, nameNew);
        } catch (final FileAlreadyExistsException e) {
            log.debug(e.getLocalizedMessage());
            return doRename(file, getFileAlreadyExistsFileName(nameNew, fileNameCounter));
        }
    }

    public String getFileAlreadyExistsFileName(String nameNew, int fileNameCounter) {
        final String extension = FilenameUtils.getExtension(nameNew);
        String baseName = FilenameUtils.getBaseName(nameNew);
        final Matcher matcher = pattern.matcher(baseName);
        if (matcher.matches()) {
            final String group = matcher.group(1);
            final int index = matcher.start(1);
            baseName = baseName.substring(0, index - 1); // also omit '_'
            // string
            fileNameCounter = Integer.parseInt(group);
        }
        fileNameCounter++;
        nameNew = baseName + "_copy" + fileNameCounter + "." + extension;
        return nameNew;
    }

    protected String getNameOld(final Path file) {
        return file.getFileName().toString();
    }

    public String getReplacementStringFrom() {
        return replacementStringFrom;
    }

    public String getReplacementStringTo() {
        return replacementStringTo;
    }

    @Override
    public Path rename(final Path file, final BasicFileAttributes attrs) throws IOException, InterruptedException {
        return doRename(file, getNameNew(file));
    }

    @Override
    public RenamingStrategyProto setReplacementStringFrom(final String replacementStringFrom) {
        this.replacementStringFrom = replacementStringFrom;
        return this;
    }

    @Override
    public RenamingStrategyProto setReplacementStringTo(final String replacementStringTo) {
        this.replacementStringTo = replacementStringTo;
        return this;
    }

    @Override
    public String toString() {
        return getName();
    }
}
