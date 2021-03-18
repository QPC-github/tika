/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.tika.parser.internal;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import org.apache.tika.detect.DefaultDetector;
import org.apache.tika.detect.Detector;
import org.apache.tika.parser.DefaultParser;
import org.apache.tika.parser.Parser;

public class Activator implements BundleActivator {

    private ServiceRegistration detectorService;

    private ServiceRegistration parserService;

    @Override
    public void start(BundleContext context) throws Exception {
        detectorService = context.registerService(Detector.class.getName(),
                new DefaultDetector(Activator.class.getClassLoader()), new Hashtable<>());
        Parser parser = new DefaultParser(Activator.class.getClassLoader());
        parserService = context.registerService(Parser.class.getName(), parser, new Hashtable<>());
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        parserService.unregister();
        detectorService.unregister();
    }

}
