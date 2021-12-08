package com.fernandoboluda.categories.and.events.service;

import com.fernandoboluda.categories.and.events.repository.SportsStorage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrinterServiceTest {

    private PrinterService service;
    private SportsStorage sportsStorage;
    private InitialLoader initialLoader;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;


    @BeforeEach
    void init() {
        sportsStorage = new SportsStorage(new HashMap<>());
        initialLoader = new InitialLoader(sportsStorage);
        service = new PrinterService();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void restoreStreams(){
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void shouldPrintCategoriesAndEvents() {
        initialLoader.load();
        outContent.reset();

        service.printCategoriesAndEvents(sportsStorage);

        assertEquals("[1] Deportes\n" +
                    "├── [2] Fútbol\n" +
                    "│   └── [3] Liga BBVA\n" +
                    "│       ├── [4] Real Madrid - Barcelona\n" +
                    "│       └── [5] Real Madrid - Villarreal\n" +
                    "└── [6] Baloncesto\n" +
                    "    └── [7] Euroliga\n" +
                    "        └── [8] Final Four\n" +
                    "\n" +
                    "[9] DeleteMe\n" +
                    "\n", outContent.toString());
    }

    @Test
    void shouldPrintEventsIds() {
        initialLoader.load();
        outContent.reset();

        service.printEventsIds("Real Madrid - Barcelona,Final Four",sportsStorage);

        assertEquals("4,8\n", outContent.toString());
    }

    @Test
    void shouldPrintCategoryId() {
        initialLoader.load();
        outContent.reset();

        service.printCategoryId("Baloncesto",sportsStorage);

        assertEquals("6\n", outContent.toString());
    }

    @Test
    void shouldPrintEventsBreadcrumbs() {
        initialLoader.load();
        outContent.reset();

        service.printEventsBreadcrumb("4,8", sportsStorage);

        assertEquals("[4] Real Madrid - Barcelona: Deportes -> Fútbol -> Liga BBVA \n" +
                    "[8] Final Four: Deportes -> Baloncesto -> Euroliga \n" +
                    "\n", outContent.toString());
    }

}