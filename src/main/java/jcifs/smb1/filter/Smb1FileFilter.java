/*
 * Copyright (c) 2018, Loong Wan (https://github.com/loong10k).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package jcifs.smb1.filter;

import org.codelibs.jcifs.smb1.SmbFileFilter;
import org.codelibs.jcifs.smb1.SmbFilenameFilter;

/**
 * Unified file filter interface for SMB1 protocol that combines both {@link SmbFileFilter}
 * and {@link SmbFilenameFilter} capabilities.
 *
 * @author [@Loong Wan](https://github.com/loong10k)
 * @since 3.0.0
 * @see AbstractFileFilter
 * @see Smb1FileFilters
 */
public interface Smb1FileFilter extends SmbFileFilter, SmbFilenameFilter {

}
