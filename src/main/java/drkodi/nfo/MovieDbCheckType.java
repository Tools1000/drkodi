/*
 *     Dr.Rename - A Minimalistic Batch Renamer
 *
 *     Copyright (C) 2022
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as
 *     published by the Free Software Foundation, either version 3 of the
 *     License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package drkodi.nfo;

public enum MovieDbCheckType {

    NOT_FOUND("Not found"){
        @Override
        public boolean isWarning() {
            return true;
        }
    },
    ORIGINAL_TITEL("Original title"), LOCALIZED_TITLE("Localized title");

    private final String name;

    MovieDbCheckType(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isWarning() {
        return false;
    }
}
