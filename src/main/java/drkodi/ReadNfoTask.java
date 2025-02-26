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

import drkodi.nfo.NfoFileParser2;
import javafx.concurrent.Task;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;

@Slf4j
public class ReadNfoTask extends Task<NfoFileParser2.ParseResult> {

    private final NfoFileParser2 parser;

    private final Path nfoPath;

    public ReadNfoTask(Path nfoPath) {
        this.nfoPath = nfoPath;
        this.parser = new NfoFileParser2();
    }

    @Override
    protected NfoFileParser2.ParseResult call() throws Exception {
        log.debug("Parsing NFO data from {}", nfoPath);
        var data = parser.parse(nfoPath);
        return data;
    }

    @Override
    public String toString() {
        return "ReadNfoTask{" +
                "nfoPath=" + nfoPath +
                '}';
    }
}
