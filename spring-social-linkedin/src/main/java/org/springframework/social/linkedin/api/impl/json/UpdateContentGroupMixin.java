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
package org.springframework.social.linkedin.api.impl.json;

import java.io.IOException;
import java.util.List;

import org.springframework.social.linkedin.api.MemberGroup;
import org.springframework.social.linkedin.api.UrlResource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
abstract class UpdateContentGroupMixin {

	@JsonCreator
	UpdateContentGroupMixin (
		@JsonProperty("id") String id, 
		@JsonProperty("firstName") String firstName, 
		@JsonProperty("lastName") String lastName, 
		@JsonProperty("headline") String headline, 
		@JsonProperty("industry") String industry, 
		@JsonProperty("publicProfileUrl") String publicProfileUrl, 
		@JsonProperty("siteStandardProfileRequest") UrlResource siteStandardProfileRequest, 
		@JsonProperty("pictureUrl") String profilePictureUrl) {}
	
	@JsonProperty("memberGroups")
	@JsonDeserialize(using=MemberGroupsListDeserializer.class)
	List<MemberGroup> memberGroups;
	
	private static class MemberGroupsListDeserializer extends JsonDeserializer<List<MemberGroup>> {
		@SuppressWarnings("unchecked")
		@Override
		public List<MemberGroup> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new LinkedInModule());
			jp.setCodec(mapper);
			if(jp.hasCurrentToken()) {
				JsonNode dataNode = jp.readValueAs(JsonNode.class).get("values");
				return (List<MemberGroup>) mapper.reader(new TypeReference<List<MemberGroup>>() {}).readValue(dataNode);
			}
			
			return null;
		}
	}

}
