import cs.vsu.ru.kapustin.Data;
import cs.vsu.ru.kapustin.FindingCandiesAndRemainder;
import cs.vsu.ru.kapustin.utils.Utils;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

public class FindingCandiesAndRemainderTest {
    FindingCandiesAndRemainder finding;

    @Test
    public void testFindingCandiesAndRemainderFor3CandiesAnd50Budget() throws FileNotFoundException {
        Data data = Utils.readDataFromFile("testSrc/testFiles/inputForTestFindingCandiesAndRemainder01.txt");
        finding = new FindingCandiesAndRemainder(data.getNames(), data.getPrices(), data.getBudget());
        Data actualData = finding.findCandiesAndRemainder();

        Data expectedData = Utils.readDataFromFile("testSrc/testFiles/answerForFindingCandiesAndRemainder01.txt");

        Assert.assertEquals(expectedData.getNames(), actualData.getNames());
        Assert.assertEquals(expectedData.getBudget(), actualData.getBudget(), 0.001);
    }

    @Test
    public void testFindingCandiesAndRemainderFor4CandiesAnd50Budget() throws FileNotFoundException {
        Data data = Utils.readDataFromFile("testSrc/testFiles/inputForTestFindingCandiesAndRemainder02.txt");
        finding = new FindingCandiesAndRemainder(data.getNames(), data.getPrices(), data.getBudget());
        Data actualData = finding.findCandiesAndRemainder();

        Data expectedData = Utils.readDataFromFile("testSrc/testFiles/answerForFindingCandiesAndRemainder02.txt");

        Assert.assertEquals(expectedData.getNames(), actualData.getNames());
        Assert.assertEquals(expectedData.getBudget(), actualData.getBudget(), 0.001);
    }

    @Test
    public void testFindingCandiesAndRemainderFor4CandiesAnd55Budget() throws FileNotFoundException {
        Data data = Utils.readDataFromFile("testSrc/testFiles/inputForTestFindingCandiesAndRemainder03.txt");
        finding = new FindingCandiesAndRemainder(data.getNames(), data.getPrices(), data.getBudget());
        Data actualData = finding.findCandiesAndRemainder();

        Data expectedData = Utils.readDataFromFile("testSrc/testFiles/answerForFindingCandiesAndRemainder03.txt");

        Assert.assertEquals(expectedData.getNames(), actualData.getNames());
        Assert.assertEquals(expectedData.getBudget(), actualData.getBudget(), 0.001);
    }

    @Test
    public void testFindingCandiesAndRemainderFor5CandiesAnd60Budget() throws FileNotFoundException {
        Data data = Utils.readDataFromFile("testSrc/testFiles/inputForTestFindingCandiesAndRemainder04.txt");
        finding = new FindingCandiesAndRemainder(data.getNames(), data.getPrices(), data.getBudget());
        Data actualData = finding.findCandiesAndRemainder();

        Data expectedData = Utils.readDataFromFile("testSrc/testFiles/answerForFindingCandiesAndRemainder04.txt");

        Assert.assertEquals(expectedData.getNames(), actualData.getNames());
        Assert.assertEquals(expectedData.getBudget(), actualData.getBudget(), 0.001);
    }

    @Test
    public void testFindingCandiesAndRemainderFor8CandiesAnd65Budget() throws FileNotFoundException {
        Data data = Utils.readDataFromFile("testSrc/testFiles/inputForTestFindingCandiesAndRemainder05.txt");
        finding = new FindingCandiesAndRemainder(data.getNames(), data.getPrices(), data.getBudget());
        Data actualData = finding.findCandiesAndRemainder();

        Data expectedData = Utils.readDataFromFile("testSrc/testFiles/answerForFindingCandiesAndRemainder05.txt");

        Assert.assertEquals(expectedData.getNames(), actualData.getNames());
        Assert.assertEquals(expectedData.getBudget(), actualData.getBudget(), 0.001);
    }
}