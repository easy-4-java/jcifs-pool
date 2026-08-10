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
package jcifs.smb.filter;

import java.util.List;

/**
 * Interface for file filters that support composition of multiple child filters.
 * Implementations allow adding, removing, and managing a list of {@link Smb2FileFilter} instances.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 3.0.0
 * @see AndFileFilter
 * @see OrFileFilter
 */
public interface ConditionalFileFilter {

    void addFileFilter(Smb2FileFilter IOSmbFileFilter);

    List<Smb2FileFilter> getFileFilters();

    boolean removeFileFilter(Smb2FileFilter IOSmbFileFilter);

    void setFileFilters(List<Smb2FileFilter> fileFilters);

}
