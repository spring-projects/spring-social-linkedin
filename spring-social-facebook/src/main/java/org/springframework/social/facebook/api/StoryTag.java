/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.facebook.api;

public class StoryTag extends FacebookObject {
	private final String id;
	private final String name;
	private final Integer offset;
	private final Integer length;
	
	public StoryTag(String id, String name, Integer offset, Integer length) {
		this.id = id;
		this.name = name;
		this.offset = offset;
		this.length = length;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getOffset() {
		return offset;
	}

	public Integer getLength() {
		return length;
	}
}