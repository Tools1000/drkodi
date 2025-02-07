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

package drkodi;

public enum MediaFileNameType {
    MATCHES_DIR_NAME("Media file names match directory name"), EXCEPTION("Failed to get media names"){
        @Override
        public boolean isWarning() {
            return true;
        }
    }, NO_MEDIA_FILES_FOUND("No media files found"){
        @Override
        public boolean isWarning() {
            return true;
        }
    }, INVALID_MEDIA_FILE_NAME("Invalid media file name"){
        @Override
        public boolean isWarning() {
            return true;
        }
    };

    private final String name;

    MediaFileNameType(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isWarning(){
        return false;
    }
}
