package com.fernandoboluda.categories.and.events;

import com.fernandoboluda.categories.and.events.repository.SportsStorage;
import com.fernandoboluda.categories.and.events.service.CategoryService;
import com.fernandoboluda.categories.and.events.service.InitialLoader;
import com.fernandoboluda.categories.and.events.service.PrinterService;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SportsStorage sportsStorage = new SportsStorage(new HashMap<>());
        CategoryService categoryService = new CategoryService(sportsStorage);
        PrinterService printerService = new PrinterService();
        InitialLoader initialLoader = new InitialLoader(sportsStorage);
        Scanner scanner = new Scanner(System.in);
        boolean execute = true;

        while (execute) {
            printMenu();
            int menuOption = checkMenuOptionSelected(scanner);
            switch (menuOption) {
                case 1:
                    initialLoader.load();
                    break;
                case 2:
                    printerService.printCategoriesAndEvents(sportsStorage);
                    break;
                case 3:
                    System.out.println("Please introduce the events separated by comma:");
                    scanner.nextLine();
                    String events = scanner.nextLine();
                    printerService.printEventsIds(events, sportsStorage);
                    break;
                case 4:
                    System.out.println("Please introduce the events ids separated by comma:");
                    scanner.nextLine();
                    String ids = scanner.nextLine();
                    printerService.printEventsBreadcrumb(ids,sportsStorage);
                    break;
                case 5:
                    System.out.println("Please introduce the category name:");
                    scanner.nextLine();
                    String categoryName = scanner.nextLine();
                    printerService.printCategoryId(categoryName, sportsStorage);
                    break;
                case 6:
                    System.out.println("Please introduce the category id:");
                    scanner.nextLine();
                    String categoryId = scanner.nextLine();
                    if(categoryService.removeCategory(Long.parseLong(categoryId))){
                        System.out.println("Category deleted.");
                    }
                    break;
                case 7:
                    execute = false;
                    break;
                default:
                    break;
            }

        }
    }


    private static void printMenu() {
        System.out.println("");
        System.out.println("CATEGORIES AND EVENTS MANAGER");
        System.out.println("----------------------------------------------------------------");
        System.out.println("1.Initial categories and events load");
        System.out.println("2.Print categories and events directory");
        System.out.println("3.Retrieve events ids(please write the events you want to retrieve separated by comma)");
        System.out.println("4.List events breadcumbs by id");
        System.out.println("5.Get category id");
        System.out.println("6.Delete a category by id");
        System.out.println("7.Exit");
        System.out.println("Please introduce a number to do respective action and push enter");
    }

    private static int checkMenuOptionSelected(Scanner scanner) {
        int menuInput;
        do {
            try {
                menuInput = scanner.nextInt();
                if ((menuInput < 1 || menuInput > 7)) {
                    System.out.print("Invalid value! Valid values are from 1 to 7): ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid value! Valid values are from 1 to 7): ");
                menuInput = -1;
                scanner.nextLine();
            }
        } while ((menuInput < 1 || menuInput > 7));

        return menuInput;
    }

}
