package sample;

import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readFile{
	
	public static void main(String[] args) throws Exception{
	    try {
	    	// 경로에 있는 파일을 읽기
	        FileInputStream file = new FileInputStream("2018K리그.xlsx");
	        XSSFWorkbook workbook = new XSSFWorkbook(file);
	        int rowNo = 0;
	        int cellIndex = 0;
	        
	        XSSFSheet sheet = workbook.getSheetAt(0); // 0 번째 시트를 가져온다 
	        										  // 만약 시트가 여러개 인 경우 for 문을 이용하여 각각의 시트를 가져온다
	        int rows = sheet.getPhysicalNumberOfRows(); // 사용자가 입력한 엑셀 Row수를 가져온다
	        
	        List<Type> typeList = new ArrayList<Type>();
	       
	        
	        for(rowNo = 0; rowNo < rows; rowNo++){
	        	
	            XSSFRow row = sheet.getRow(rowNo);
	            if(row != null){
	                int cells = row.getPhysicalNumberOfCells(); // 해당 Row에 사용자가 입력한 셀의 수를 가져온다
	                for(cellIndex = 0; cellIndex <= cells; cellIndex++){  
	                    XSSFCell cell = row.getCell(cellIndex); // 셀의 값을 가져온다	        
	                    String value = "";	                    
	                    if(cell == null){ // 빈 셀 체크 
	                        continue;
	                    }else{
	                        // 타입 별로 내용을 읽는다
	                        switch (cell.getCellType()){
	                        case XSSFCell.CELL_TYPE_FORMULA:
	                            value = cell.getCellFormula();
	                            break;
	                        case XSSFCell.CELL_TYPE_NUMERIC:
	                        	 double cellValue = cell.getNumericCellValue();
	                        	    if (cellValue == Math.rint(cellValue)) { // Math.rint를 이용 	                        	 
	                        	        value = String.valueOf((int) cellValue); // Math.rint는 가장 가까운 int 값을 찾아 double 형태로 변환	
	                        	    } else {
	                        	        value = String.valueOf(cellValue);
	                        	    }	                                                      	                           	    
	                            break;
	                        case XSSFCell.CELL_TYPE_STRING:
	                            value = cell.getStringCellValue() + "";
	                           /*
	                            if(value.equals("팀")||value.equals("경기수")||value.equals("승")||value.equals("무")||value.equals("패"))
	                            	type[cellIndex].setType(value);
	                            else 
	                            	teams[rowNo].setTeamName(value);
	                           */
	                            break;
	                        case XSSFCell.CELL_TYPE_BLANK:
	                            value = cell.getBooleanCellValue() + "";
	                            break;
	                        case XSSFCell.CELL_TYPE_ERROR:
	                            value = cell.getErrorCellValue() + "";
	                            break;
	                        }
	                    }
	                    System.out.print(value + " ");
	                }
	                System.out.println("");
	            }
	        }
	    }catch(Exception e) {
    		e.printStackTrace();
    	}
	}
}