package exercise;

class App {

    public static void main(String[] args) throws InterruptedException {
        // BEGIN
        SafetyList safetyList = new SafetyList();
        ListThread t1 = new ListThread(safetyList);
        ListThread t2 = new ListThread(safetyList);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(safetyList.getSize());
        // END
    }
}

