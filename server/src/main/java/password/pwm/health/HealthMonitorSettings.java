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

package password.pwm.health;

import password.pwm.AppProperty;
import password.pwm.config.AppConfig;
import password.pwm.util.java.TimeDuration;

record HealthMonitorSettings(
        boolean healthCheckEnabled,
        TimeDuration nominalCheckInterval,
        TimeDuration minimumCheckInterval,
        TimeDuration maximumRecordAge,
        TimeDuration maximumForceCheckWait
        )
{
    static HealthMonitorSettings fromConfiguration( final AppConfig config )
    {
        return new HealthMonitorSettings(
                Boolean.parseBoolean( config.readAppProperty( AppProperty.HEALTHCHECK_ENABLED ) ),
                TimeDuration.of( Long.parseLong( config.readAppProperty( AppProperty.HEALTHCHECK_NOMINAL_CHECK_INTERVAL ) ), TimeDuration.Unit.SECONDS ),
                TimeDuration.of( Long.parseLong( config.readAppProperty( AppProperty.HEALTHCHECK_MIN_CHECK_INTERVAL ) ), TimeDuration.Unit.SECONDS ),
                TimeDuration.of( Long.parseLong( config.readAppProperty( AppProperty.HEALTHCHECK_MAX_RECORD_AGE ) ), TimeDuration.Unit.SECONDS ),
                TimeDuration.of( Long.parseLong( config.readAppProperty( AppProperty.HEALTHCHECK_MAX_FORCE_WAIT ) ), TimeDuration.Unit.SECONDS ) );
    }
}
