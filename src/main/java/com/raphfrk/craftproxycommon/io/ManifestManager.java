/*
 * This file is part of CraftProxyCommon.
 *
 * Copyright (c) 2013-2014, Raphfrk <http://raphfrk.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.raphfrk.craftproxycommon.io;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map.Entry;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class ManifestManager {
	
	public static Integer getInt(String key) {
		String s = get(key);
		if (s == null) {
			return null;
		}
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return null;
		}
	}
	
	public static String get(String key) {
		
		Manifest m;
		try {
			m = getManifest();
			if (m == null) {
				return null;
			}
		} catch (IOException e) {
			return null;
		}
		
		Attributes a = m.getMainAttributes();
		
		if (a == null) {
			return null;
		}

		return a.getValue(key);
	}
	
	private static Manifest getManifest() throws IOException {
		URL url = ManifestManager.class.getProtectionDomain().getCodeSource().getLocation();

		if (url == null) {
			return null;
		}
		
		File file;
		try {
			file = new File(url.toURI());
		} catch (URISyntaxException e) {
			throw new IOException(e);
		}
		
		JarFile jarFile = new JarFile(file);
		
		return jarFile.getManifest();

	}

}
