/*
 * Copyright (c) 2022, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.organization.management.application.dao;

import org.wso2.carbon.identity.organization.management.application.model.MainApplicationDO;
import org.wso2.carbon.identity.organization.management.application.model.SharedApplicationDO;
import org.wso2.carbon.identity.organization.management.service.exception.OrganizationManagementException;
import org.wso2.carbon.identity.organization.management.service.exception.OrganizationManagementServerException;

import java.util.List;
import java.util.Optional;

/**
 * This interface performs CRUD operations for Shared applications.
 */
public interface OrgApplicationMgtDAO {

    /**
     * Creates new entry for shared applications across organizations.
     *
     * @param mainAppId             Unique identifier of the main application.
     * @param ownerOrgId            The unique ID corresponding to the organization where the main application resides.
     * @param sharedAppId           Unique identifier of the shared application.
     * @param sharedOrgId           The unique ID of the organization, to whom the application is shared.
     * @param shareWithAllChildren  Attribute indicating if the application is shared with all child organizations.
     * @throws OrganizationManagementException the server exception is thrown in a failure to create the entry.
     */
    void addSharedApplication(String mainAppId, String ownerOrgId, String sharedAppId, String sharedOrgId,
            boolean shareWithAllChildren) throws OrganizationManagementException;

    /**
     * Retrieve the list of shared applications entries for a given application.
     *
     * @param organizationId The unique ID corresponding to the organization where the main application resides.
     * @param applicationId  Unique identifier of the main application.
     * @return list of {@link SharedApplicationDO}s for a given application.
     * @throws OrganizationManagementException the server exception is thrown in a failure to create the entry.
     */
    List<SharedApplicationDO> getSharedApplications(String organizationId, String applicationId)
            throws OrganizationManagementException;

    /**
     * Retrieve main application for a given shared application.
     *
     * @param sharedAppId Unique identifier of the shared application.
     * @param sharedOrgId The unique ID of the organization, to whom the application is shared.
     * @return {@link MainApplicationDO} for a given shared application.
     * @throws OrganizationManagementException the server exception is thrown in a failure to retrieve the entry.
     */
    Optional<MainApplicationDO> getMainApplication(String sharedAppId, String sharedOrgId)
            throws OrganizationManagementException;

    /**
     * Retrieve shared application for a given shared application id.
     *
     * @param sharedAppId Unique identifier of the shared application.
     * @param sharedOrgId The unique ID of the organization, to whom the application is shared.
     * @return {@link SharedApplicationDO}
     * @throws OrganizationManagementException the server exception is thrown in a failure to retrieve the entry.
     */
    Optional<SharedApplicationDO> getSharedApplication(int sharedAppId, String sharedOrgId)
            throws OrganizationManagementException;

    /**
     * Returns the unique identifier of the shared application.
     *
     * @param mainAppId   Main application identifier.
     * @param ownerOrgId  The unique ID corresponding to the organization where the main application resides.
     * @param sharedOrgId The unique ID of the organization, to whom the application is shared.
     * @return Unique identifier of the shared application.
     * @throws OrganizationManagementException the server exception is thrown in a failure to retrieve the entry.
     */
    Optional<String> getSharedApplicationResourceId(String mainAppId, String ownerOrgId, String sharedOrgId)
            throws OrganizationManagementException;

    /**
     * Returns whether the given application has fragment applications.
     *
     * @param applicationId Application ID.
     * @return boolean value indicating whether the given application has fragments created.
     * @throws OrganizationManagementException the server exception is thrown in a failure when checking the fragments
     *                                         of the application.
     */
    boolean hasFragments(String applicationId) throws OrganizationManagementException;

    /**
     * Returns whether the given application is a fragment application.
     *
     * @param applicationId Application ID.
     * @return boolean value indicating whether the given application is a fragment application.
     * @throws OrganizationManagementException the server exception is thrown in a failure when checking if the
     *                                         application is a fragment.
     */
    boolean isFragmentApplication(int applicationId) throws OrganizationManagementException;

    /**
     * Update the shareWithAllChildren value of shared applications for a given main application.
     *
     * @param mainApplicationId     The unique ID of the main application.
     * @param ownerOrganizationId   The unique ID corresponding to the organization where the main application resides.
     * @param shareWithAllChildren  Value to be updated.
     * @throws OrganizationManagementException the server exception is thrown in a failure when updating.
     */
    void updateShareWithAllChildren(String mainApplicationId, String ownerOrganizationId, boolean shareWithAllChildren)
            throws OrganizationManagementException;

    void deleteSharedAppLink(String organizationId) throws OrganizationManagementServerException;
}
