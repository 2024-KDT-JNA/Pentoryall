package com.pentoryall.series.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/series")
public class SeriesController {
    @GetMapping("add")
    public void addSeries(){

    }
    @GetMapping("/page")
    public void seriesPage(){

    }
}
