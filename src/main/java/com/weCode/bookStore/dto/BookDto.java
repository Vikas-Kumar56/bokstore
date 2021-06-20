package com.weCode.bookStore.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "BookDto", description = "All details about book")
public class BookDto {

    @ApiModelProperty(
            readOnly = true,
            value = "UUID",
            dataType = "UUID",
            example = "b5607d38-8fc1-43ef-b44e-34967083c80a",
            notes = "The database generated uuid for book id",
            required = true
    )
    private UUID id;

    @ApiModelProperty(
            value = "title",
            dataType = "String",
            example = "book title",
            notes = "Book title",
            required = true
    )
    private String title;

    @ApiModelProperty(
            value = "description",
            dataType = "String",
            example = "book description",
            notes = "Book description",
            required = true
    )
    private String description;

    @ApiModelProperty(
            value = "releaseYear",
            dataType = "int",
            example = "2020",
            notes = "Book releaseYear",
            required = true
    )
    private int releaseYear;
}
