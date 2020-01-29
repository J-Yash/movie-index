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

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Index all text files under a directory.
 * <p>
 * This is a command-line application demonstrating simple Lucene indexing. Run
 * it with no command-line arguments for usage information.
 */
public class generateMovieIndex {

	private generateMovieIndex() {
	}

	/** Index all text files under a directory. 
	 * @throws IOException */
	public static void main(String[] args) throws IOException {
		HashMap<String, String> plot_dict = new HashMap();
		HashMap<String, String> title_dict = new HashMap();
		plot_dict = createPlotDict();
		title_dict = createTitleDict();
		
		try {
			String indexPath = "./wikiindexStandard";
			System.out.println("Indexing to directory '" + indexPath + "'...");
			Directory dir = FSDirectory.open(Paths.get(indexPath));
			Analyzer analyzer = new StandardAnalyzer();
			IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
			iwc.setOpenMode(OpenMode.CREATE);
			IndexWriter writer = new IndexWriter(dir, iwc);
			for (String movieID : plot_dict.keySet()) {
				if (title_dict.get(movieID) != null && plot_dict.get(movieID) != null) {
				indexDoc(writer, movieID, title_dict.get(movieID), plot_dict.get(movieID));
				}
			}
			String pathToIndex = "./wikiindexStandard";
			IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(pathToIndex)));
			IndexSearcher searcher = new IndexSearcher(reader);
			double totalDocsInIndex = reader.numDocs();
			System.out.println("Num of docs: " + totalDocsInIndex);
			writer.close();
			System.out.println("Done ...");
		} catch (IOException e) {
			System.out.println(" caught a " + e.getClass()
			+ "\n with message: " + e.getMessage());
		}
		
		try {
			String indexPath = "./wikiindexSimple";
			System.out.println("Indexing to directory '" + indexPath + "'...");
			Directory dir = FSDirectory.open(Paths.get(indexPath));
			Analyzer analyzer = new SimpleAnalyzer();
			IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
			iwc.setOpenMode(OpenMode.CREATE);
			IndexWriter writer = new IndexWriter(dir, iwc);
			for (String movieID : plot_dict.keySet()) {
				if (title_dict.get(movieID) != null && plot_dict.get(movieID) != null) {
				indexDoc(writer, movieID, title_dict.get(movieID), plot_dict.get(movieID));
				}
			}
			String pathToIndex = "./wikiindexSimple";
			IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(pathToIndex)));
			IndexSearcher searcher = new IndexSearcher(reader);
			double totalDocsInIndex = reader.numDocs();
			System.out.println("Num of docs: " + totalDocsInIndex);
			writer.close();
			System.out.println("Done ...");
		} catch (IOException e) {
			System.out.println(" caught a " + e.getClass()
			+ "\n with message: " + e.getMessage());
		}
		
		try {
			String indexPath = "./wikiindexStop";
			System.out.println("Indexing to directory '" + indexPath + "'...");
			Directory dir = FSDirectory.open(Paths.get(indexPath));
			Analyzer analyzer = new StopAnalyzer();
			IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
			iwc.setOpenMode(OpenMode.CREATE);
			IndexWriter writer = new IndexWriter(dir, iwc);
			for (String movieID : plot_dict.keySet()) {
				if (title_dict.get(movieID) != null && plot_dict.get(movieID) != null) {
				indexDoc(writer, movieID, title_dict.get(movieID), plot_dict.get(movieID));
				}
			}
			String pathToIndex = "./wikiindexStop";
			IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(pathToIndex)));
			IndexSearcher searcher = new IndexSearcher(reader);
			double totalDocsInIndex = reader.numDocs();
			System.out.println("Num of docs: " + totalDocsInIndex);
			writer.close();
			System.out.println("Done ...");
		} catch (IOException e) {
			System.out.println(" caught a " + e.getClass()
			+ "\n with message: " + e.getMessage());
		}
		
		try {
			String indexPath = "./wikiindexKeyword";
			System.out.println("Indexing to directory '" + indexPath + "'...");
			Directory dir = FSDirectory.open(Paths.get(indexPath));
			Analyzer analyzer = new KeywordAnalyzer();
			IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
			iwc.setOpenMode(OpenMode.CREATE);
			IndexWriter writer = new IndexWriter(dir, iwc);
			for (String movieID : plot_dict.keySet()) {
				if (title_dict.get(movieID) != null && plot_dict.get(movieID) != null) {
				indexDoc(writer, movieID, title_dict.get(movieID), plot_dict.get(movieID));
				}
			}
			String pathToIndex = "./wikiindexKeyword";
			IndexReader reader = DirectoryReader.open(FSDirectory.open(Paths.get(pathToIndex)));
			IndexSearcher searcher = new IndexSearcher(reader);
			double totalDocsInIndex = reader.numDocs();
			System.out.println("Num of docs: " + totalDocsInIndex);
			writer.close();
			System.out.println("Done ...");
		} catch (IOException e) {
			System.out.println(" caught a " + e.getClass()
			+ "\n with message: " + e.getMessage());
		}
		
		System.out.println("Indexing Complete.");
	}

	/** Indexes a single document 
	 * @throws IOException */
	static void indexDoc(IndexWriter writer, String movieID, String movieTitle, String moviePlot) throws IOException {
		// make a new, empty document
		Document lDoc = new Document();

		lDoc.add(new StringField("ID", movieID,
				Field.Store.YES));

		lDoc.add(new TextField("TITLE", movieTitle,
				Field.Store.YES));

		lDoc.add(new TextField("PLOT", moviePlot, Field.Store.YES));
		writer.addDocument(lDoc);
	}
	
	// Method to create the plot dict.
	static HashMap<String, String> createPlotDict() throws IOException{
		String filename = "/home/yash/eclipse-workspace/MovieIndex/wikicorpus/MovieSummaries/plot_summaries.txt";
		// 1. Read the entire file and convert into string
		String content = new String(Files.readAllBytes(Paths.get(filename)));
		
		// 2. Use string utils to split by \n
		
		String[] summaries = content.split("\n");
		//System.out.println(summaries.length);
		//System.out.println(summaries[0]);
		
		// 3. Split each line by \t
		// 4. Create a dictionary of ID to string
		
		HashMap<String, String> movie_plots = new HashMap<String, String>();
		
		
		for (String summary : summaries) {
			String[] movie = summary.split("\t");
			//System.out.println(movie.length);
			
			movie_plots.put(movie[0], movie[1]);
			
			//System.out.println(movie_plots);
			//break;
		}
		
		//System.out.println(movie_plots.size());
		
		return movie_plots;
		
	}
	
	// Method to create the Title dict.
		static HashMap<String, String> createTitleDict() throws IOException{
			String filename = "/home/yash/eclipse-workspace/MovieIndex/wikicorpus/MovieSummaries/movie.metadata.tsv";
			
			String metadata = new String(Files.readAllBytes(Paths.get(filename)));
			
			// 2. Use string utils to split by \n
			
			String[] movie_titles = metadata.split("\n");
			//System.out.println("++++++++++++");
			//System.out.println(movie_titles.length);
			//System.out.println(movie_titles[0]);
			
			// 3. Split each line by \t
			// 4. Create a dictionary of ID to string
			
			HashMap<String, String> all_movies = new HashMap<String, String>();
			
			
			for (String title : movie_titles) {
				String[] movie = title.split("\t");
				//System.out.println(movie.length);
				
				all_movies.put(movie[0], movie[2]);
				
				//System.out.println(all_movies);
				//break;
			}
			
			//System.out.println(all_movies.size());
			
			
			
			
			return all_movies;
		}
}
