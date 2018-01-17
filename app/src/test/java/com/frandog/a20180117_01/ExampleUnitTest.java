package com.frandog.a20180117_01;

import com.frandog.a20180117_01.data.Student;
import com.frandog.a20180117_01.data.StudentScoreDAO;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
//    @Test
//    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);
//    }
//    @Test
//    public void test1() throws Exception {
//        assertEquals(9,6+3);
//    }
//    @Test
//    public void test2() throws Exception {
//        assertEquals(9,6+8);
//    }
//    @Test
//    public void test3() throws Exception {
//        assertEquals("1",1);
//    }
//    @Test
//    public void test4() throws Exception {
//        assertEquals(10,MyTest.add(5,5));
//    }
    @Test
    public void test_add_data() throws Exception{
        StudentScoreDAO dao = new StudentScoreDAO();
        dao.add(new Student(1,"Bob",90));
        dao.add(new Student(2,"Mary",50));
        assertEquals(2,dao.getList().size());
    }
    @Test
    public void test_add_data2() throws Exception{
        StudentScoreDAO dao = new StudentScoreDAO();
        dao.add(new Student(1,"Bob",90));
        dao.add(new Student(2,"Mary",50));
        assertEquals(50,dao.getList().get(1).score);
    }
    @Test
    public void test_getStudent() throws Exception{
        StudentScoreDAO dao = new StudentScoreDAO();
        dao.add(new Student(1,"Bob",90));
        dao.add(new Student(2,"Mary",50));
//        dao.add(new Student(2,"Mary",100));       沒辦法蓋過
        assertEquals(50,dao.getStudent(2).score);
    }
    @Test
    public void test_getStudent1() throws Exception{
        StudentScoreDAO dao = new StudentScoreDAO();
        dao.add(new Student(1,"Bob",90));
        dao.add(new Student(2,"Mary",50));
        assertEquals(null,dao.getStudent(3));   //寫dao.getStudent(3).score會出錯
    }

    @Test
    public void test_update() throws Exception {
        StudentScoreDAO dao = new StudentScoreDAO();
        dao.add(new Student(1, "Bob", 95));
        dao.add(new Student(2, "Mary", 50));
        dao.updata(new Student(2,"Helen",100));
        assertEquals(100, dao.getStudent(2).score);
    }
    public void test_delete() throws Exception {
        StudentScoreDAO dao = new StudentScoreDAO();
        dao.add(new Student(1, "Bob", 95));
        dao.add(new Student(2, "Mary", 50));
        dao.delete(2);
        assertEquals(1, dao.getList().size());
    }
    public void test_delete1() throws Exception {
        StudentScoreDAO dao = new StudentScoreDAO();
        dao.add(new Student(1, "Bob", 95));
        dao.add(new Student(2, "Mary", 50));
        dao.delete(2);
        assertEquals(1, dao.getStudent(0).id);  //確認有沒有刪錯筆
    }

}