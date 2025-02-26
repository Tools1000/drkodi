/*
 *     Dr.Kodi - A Minimalistic Batch Renamer
 *
 *     Copyright (C) 2023
 *
 *     This file is part of Dr.Kodi.
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

package drkodi.data.movie;

import drkodi.KodiUtil;
import drrename.commons.RenamingPath;
import javafx.concurrent.Task;
import lombok.RequiredArgsConstructor;

import java.nio.file.Path;
import java.util.List;

@RequiredArgsConstructor
public class RecursiveMediaFilesCollectTask extends Task<List<RenamingPath>> {

    private final Movie movie;

    private final int maxDepth;

    @Override
    protected List<RenamingPath> call() throws Exception {
        Path path = movie.getRenamingPath().getOldPath();
        return KodiUtil.findAllVideoFiles(path, maxDepth).stream().map(RenamingPath::new).toList();
    }
}
