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

package drkodi;

import drkodi.nfo.KodiConstants;
import drkodi.nfo.NfoFileNameChecker;
import drkodi.util.DrRenameUtil;
import drrename.commons.RenamingPath;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Getter
@Slf4j
public class NfoFileNameIssue extends FxKodiIssue<NfoFileCheckResult> {

    private NfoFileCheckResult checkResult;

    public NfoFileNameIssue(RenamingPath moviePath) {
        super(moviePath);
    }

    @Override
    public NfoFileCheckResult checkStatus() {
            return new NfoFileNameChecker().checkDir(getRenamingPath().getOldPath());
    }

    @Override
    public void fix(NfoFileCheckResult result) throws FixFailedException {
        try {
            DrRenameUtil.rename(result.getNfoFiles().get(0), getMovieNameFromFolder() + KodiConstants.NFO_FILE_EXTENSION);
        } catch (IOException e) {
            throw new FixFailedException(e);
        }
    }

    @Override
    public void updateStatus(NfoFileCheckResult result) {
        this.checkResult = result;

    }

    @Override
    public String getHelpText() {
        return null;
    }

    @Override
    public String getIdentifier() {
        return "NFO File Name";
    }
}
