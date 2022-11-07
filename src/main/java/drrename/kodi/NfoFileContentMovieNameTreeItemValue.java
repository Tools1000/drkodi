/*
 *     Dr.Rename - A Minimalistic Batch Renamer
 *
 *     Copyright (C) 2022
 *
 *     This file is part of Dr.Rename.
 *
 *     You can redistribute it and/or modify it under the terms of the GNU Affero
 *     General Public License as published by the Free Software Foundation, either
 *     version 3 of the License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful, but WITHOUT
 *     ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 *     FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License
 *     for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package drrename.kodi;

import drrename.kodi.nfo.NfoContentTitleChecker;
import drrename.model.RenamingPath;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Setter
@Getter
@Slf4j
public class NfoFileContentMovieNameTreeItemValue extends KodiTreeItemValue<NfoFileContentMovieNameCheckResult> {



    public NfoFileContentMovieNameTreeItemValue(RenamingPath moviePath, Executor executor, WarningsConfig warningsConfig) {
        super(moviePath, executor, warningsConfig);
        triggerStatusCheck();
    }

    @Override
    public NfoFileContentMovieNameCheckResult checkStatus() {
        return new NfoFileContentMovieNameCheckResult(new NfoContentTitleChecker().checkDir(getRenamingPath().getOldPath()));
    }

    @Override
    public void fix(NfoFileContentMovieNameCheckResult result) throws FixFailedException {
        throw new IllegalStateException("Cannot fix");
    }

    @Override
    public void updateStatus(NfoFileContentMovieNameCheckResult result) {
        if(result == null){
            return;
        }
//        log.debug("Updating status");
        setCheckResult(result);
        setWarning(calculateWarning());
    }

    private boolean calculateWarning() {
        if(getCheckResult() == null){
            return false;
        }
//        log.debug("Calculating warning");
        return getCheckResult().getType().getType().isWarning(getWarningsConfig().isMissingNfoFileIsWarning());
    }

    @Override
    protected String buildNewMessage(Boolean newValue) {
        return getCheckResult().getType().getType() + (getCheckResult().getType().getNfoFiles() == null || getCheckResult().getType().getNfoFiles().isEmpty() ? "" : ": " + getCheckResult().getType().getNfoFiles().stream().map(Path::getFileName).map(Object::toString).collect(Collectors.joining(", ")));
    }

    @Override
    public String getHelpText() {
        return null;
    }

    @Override
    public String getIdentifier() {
        return "NFO File Content Movie Name";
    }
}
