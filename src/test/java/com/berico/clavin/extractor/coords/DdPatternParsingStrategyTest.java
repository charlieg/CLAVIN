package com.berico.clavin.extractor.coords;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.berico.clavin.extractor.CoordinateOccurrence;
import com.berico.clavin.gazetteer.LatLon;

/*#####################################################################
 * 
 * CLAVIN (Cartographic Location And Vicinity INdexer)
 * ---------------------------------------------------
 * 
 * Copyright (C) 2012-2013 Berico Technologies
 * http://clavin.bericotechnologies.com
 * 
 * ====================================================================
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 * 
 * ====================================================================
 * 
 * DdPatternParsingStrategyTest.java
 * 
 *###################################################################*/


public class DdPatternParsingStrategyTest 
		extends BaseRegexParsingStrategyTest {

	DdPatternParsingStrategy strategy = new DdPatternParsingStrategy();
	
	@Test
	public void pattern_matches_decimal_degree_coordinate_with_positive_hemisphere_in_text() {
		
		String text = "I went to this great place (40.446195, 79.948862) last week.";
		
		this.assertPatternMatches(strategy, text, "40.446195, 79.948862");
	}
	
	@Test
	public void pattern_matches_decimal_degree_coordinate_with_negative_lat_hemisphere_in_text() {
		
		String text = "I went to this great place (-40.446195, 79.948862) last week.";
			
		this.assertPatternMatches(strategy, text, "-40.446195, 79.948862");
	}
	
	@Test
	public void pattern_matches_decimal_degree_coordinate_with_negative_lon_hemisphere_in_text() {
		
		String text = "I went to this great place (40.446195, -79.948862) last week.";
			
		this.assertPatternMatches(strategy, text, "40.446195, -79.948862");
	}
	
	@Test
	public void pattern_matches_decimal_degree_coordinate_with_negative_lat_lon_hemisphere_in_text() {
		
		String text = "I went to this great place (-23.399437,-52.090904) last week.";
			
		this.assertPatternMatches(strategy, text, "-23.399437,-52.090904");
	}

	@Test
	public void strategy_correctly_parses_matched_decimal_degrees_with_positive_hemispheres_string(){
		
		String testCoordinate = "40.446195, 79.948862";
		int position = 42;
		
		CoordinateOccurrence<LatLon> coordinate = 
			strategy.parse(testCoordinate, position);
		
		assertEquals(testCoordinate, coordinate.getExtractedText());
		assertEquals(position, coordinate.getPosition());
		assertEquals(40.446195, coordinate.getValue().getLatitude(), 0.0001);
		assertEquals(79.948862, coordinate.getValue().getLongitude(), 0.0001);
	}
	
	@Test
	public void strategy_correctly_parses_matched_decimal_degrees_with_negative_hemispheres_string(){
		
		String testCoordinate = "-23.399437,-52.090904";
		int position = 42;
		
		CoordinateOccurrence<LatLon> coordinate = 
			strategy.parse(testCoordinate, position);
		
		assertEquals(testCoordinate, coordinate.getExtractedText());
		assertEquals(position, coordinate.getPosition());
		assertEquals(-23.399437, coordinate.getValue().getLatitude(), 0.0001);
		assertEquals(-52.090904, coordinate.getValue().getLongitude(), 0.0001);
	}
	
}
