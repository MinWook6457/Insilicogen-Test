package sample;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readFile {
    public static void main(String[] args) {
        try {
            // 엑셀 파일을 읽기 위한 FileInputStream 생성
            FileInputStream file = new FileInputStream("2018K리그.xlsx");

            // XSSFWorkbook 객체 생성하여 엑셀 파일 로드
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // 첫 번째 시트 가져오기
            XSSFSheet sheet = workbook.getSheetAt(0);

            // 행의 수
            int rows = sheet.getPhysicalNumberOfRows();

            // 열의 수
            int cols = sheet.getRow(0).getPhysicalNumberOfCells();

            // 팀 리스트 생성
            List<Team> teams = new ArrayList<>();

            // 각 행을 순회
            for (int rowNo = 1; rowNo < rows; rowNo++) {
                XSSFRow row = sheet.getRow(rowNo);

                // 새로운 팀 객체 생성
                Team team = new Team();

                // 각 열을 순회
                for (int colNo = 0; colNo < cols; colNo++) {
                    XSSFCell cell = row.getCell(colNo);

                    // 각 셀의 값을 문자열로 변환
                    String cellValue = getCellValueAsString(cell);

                    // 각 열에 따라 팀 객체에 값을 설정
                    // Type 객체를 사용하고 싶었지만 액셀 열의 값을 아는 경우 아래 코드가 더 깔끔하다고 생각됨.
                    switch (colNo) { // 팀 , 경기수 , 승 , 무 , 패 순서
                        case 0:
                            team.setTeamName(cellValue);
                            break;
                        case 1:
                            team.setGames(Integer.parseInt(cellValue));
                            break;
                        case 2:
                            team.setWins(Integer.parseInt(cellValue));
                            break;
                        case 3:
                            team.setDraws(Integer.parseInt(cellValue));
                            break;
                        case 4:
                            team.setLosses(Integer.parseInt(cellValue));
                            break;
                        default:
                            break;
                    }
                }

                // 팀 객체를 리스트에 추가
                teams.add(team);
            }

            // 엑셀 파일 사용 후에는 닫아주어야 함
            workbook.close();
            file.close();

            // 결과 출력
            System.out.println("엑셀 파일 읽은 후 객체 저장 후 출력");
            for (Team team : teams) {
                System.out.println(team);
            }
            
            // 각 팀 승점 계산
            int wins = 0, draws = 0, losses = 0;
            for(int i = 0 ; i < teams.size() ; i++) {         	
            	wins += (teams.get(i).getWins() * 3);
            	draws += (teams.get(i).getDraws() + 1);
            	losses += (teams.get(i).getLosses());
            	
            	int total = wins + draws + losses;
            	
            	double score = (double)(total / teams.get(i).getGames());
            	
            	teams.get(i).setScore(score);
            }
            
            System.out.println("승점 계산 후 출력");
         
            // 계산 후 출력
            for (Team team : teams) {
                System.out.println(team);
            }
            
            System.out.println("내림차순 정렬 후 출력");
            // 내림차순 정렬
            // Comparable 내에 정의된 Collection 클래스 사용
            // 참고 : https://www.daleseo.com/java-comparable-comparator/
            Collections.sort(teams, (a, b) -> (int)b.getScore() - (int)a.getScore());

            // 정렬 후 출력
            for (Team team : teams) {
                System.out.println(team);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 셀의 값을 문자열로 변환하는 메서드
    private static String getCellValueAsString(XSSFCell cell) {
        if (cell == null) {
            return "";
        }

        CellType cellType = cell.getCellType();

        switch (cellType) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            default:
                return "";
        }
    }
    
    
}
