/*
 * Password Management Servlets (PWM)
 * http://www.pwm-project.org
 *
 * Copyright (c) 2006-2009 Novell, Inc.
 * Copyright (c) 2009-2021 The PWM Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package password.pwm.http.servlet.configeditor.function;

import password.pwm.config.PwmSetting;
import password.pwm.config.stored.StoredConfigKey;
import password.pwm.config.stored.StoredConfigurationModifier;
import password.pwm.error.PwmOperationalException;
import password.pwm.error.PwmUnrecoverableException;
import password.pwm.util.java.PwmUtil;

public class OAuthCertImportFunction extends AbstractUriCertImportFunction
{
    @Override
    String getUri( final StoredConfigurationModifier modifier, final StoredConfigKey key, final String extraData )
            throws PwmOperationalException, PwmUnrecoverableException
    {
        final PwmSetting pwmSetting = key.toPwmSetting();

        final String uriString;
        final String menuDebugLocation;

        final PwmSetting urlCertSetting;
        switch ( pwmSetting )
        {
            case OAUTH_ID_CERTIFICATE:
                urlCertSetting = PwmSetting.OAUTH_ID_CODERESOLVE_URL;
                break;

            case RECOVERY_OAUTH_ID_CERTIFICATE:
                urlCertSetting = PwmSetting.RECOVERY_OAUTH_ID_CODERESOLVE_URL;
                break;

            default:
                PwmUtil.unhandledSwitchStatement( pwmSetting );
                return null;
        }

        final StoredConfigKey oauthCertKey = StoredConfigKey.forSetting( urlCertSetting, key.getProfileID().orElse( null ), key.getDomainID() );
        uriString = ( String ) modifier.newStoredConfiguration().readStoredValue( oauthCertKey ).orElseThrow().toNativeObject();

        return validateUriStringSetting( uriString, oauthCertKey );
    }
}