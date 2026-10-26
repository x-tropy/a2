public class TestStation {

    public static void main(String[] args) {
        Station.resetIDCounter();

        Station s55 = new Station(55, 100, 200);
        Station s56 = new Station(56, 120, 200);
        Station s40 = new Station(40, 100, 180);

        // s55 在 A 线（next 是 s56）
        s55.setNext(s56, 'A');
        s56.setPrev(s55, 'A');

        // s55 在 C 线（prev 是 s40）
        s55.setPrev(s40, 'C');
        s40.setNext(s55, 'C');

        System.out.println(s55.toString()); // Stn 55 on Lines: A C
        System.out.println(s40.toString()); // Stn 40 on Lines: C

        // 测试 getNext / getPrev
        System.out.println(s55.getNext('A').getStnNo()); // 56
        System.out.println(s55.getPrev('C').getStnNo()); // 40

        // 测试 id 连续性
        System.out.println(s55.getID()); // 0
        System.out.println(s56.getID()); // 1
        System.out.println(s40.getID()); // 2

        // 测试 visited
        s55.visit();
        System.out.println(s55.isVisited()); // true
    }
}
