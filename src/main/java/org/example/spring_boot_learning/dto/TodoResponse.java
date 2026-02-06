package org.example.spring_boot_learning.dto;

public record TodoResponse(
        Long id,
        String title,
        Boolean completed)
{

}
