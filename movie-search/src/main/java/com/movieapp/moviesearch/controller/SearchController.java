package com.movieapp.moviesearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieapp.moviesearch.config.SearchBuilder;
import com.movieapp.moviesearch.dto.APISuccessResponseDTO;


@RestController
@CrossOrigin
@RequestMapping("/search/v1")
public class SearchController {
	public static final int HTTP_STATUS_OK=200;
	
	@Autowired
    private SearchBuilder searchBuilder;

    /**
     * @author Mahendran Dayalan
     * @param searchText
     * @return ResponseEntity
     * 
     * Get API to search data in elastic search
     */
    @GetMapping(value = "/{searchText}")
    public ResponseEntity<?> getAll(@PathVariable final String searchText) {
    	
    	APISuccessResponseDTO response = new APISuccessResponseDTO();
    	response.setHttpStatus(HttpStatus.ACCEPTED);
    	response.setStatusCode(HTTP_STATUS_OK);
    	response.setMessage("Sucessfully Fetched Data");
    	response.setBody(searchBuilder.getAll(searchText));
    	
    	
        return ResponseEntity.status(HttpStatus.OK).header("message", String.valueOf(HttpStatus.ACCEPTED)).body(response);
    }

}
