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

package drkodi.nfo;

import drkodi.NfoMovieRoot;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NfoFileYearExtractor extends AbstractNfoFileExtractor {

    public NfoFileYearExtractor(NfoFileParser nfoFileParser) {
        super(nfoFileParser);
    }

    @Override
    protected String parseNfoModel(NfoMovieRoot xmlModel) {
        return xmlModel.getMovie().getYear();
    }
}
