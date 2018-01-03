package learn.prospring5.ch08.services;

import learn.prospring5.ch08.view.SingerSummary;

import java.util.List;
public interface SingerSummaryService {
    List<SingerSummary> findAll();
}