//package Supply.service;
//
//import Modify.ModifySupply;
//import Modify.service.ModifySupplyService;
//import Supply.Measure;
//import Supply.Supply;
//import Supply.exception.MandatoryAttributeSupplyException;
//import User.User;
//import java.time.LocalDate;
//import javax.persistence.EntityManager;
//import org.mockito.Mockito;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//public class SupplyServiceTest {
//
//    @Test
//    public void createSupplyTestCorrect() {
//        //Arrange
//        EntityManager entityManager = Mockito.mock(EntityManager.class);
//        Supply newSupply = new Supply();
//        Measure measureTest = new Measure();
//        newSupply.setCode(1);
//        newSupply.setName("Prueba");
//        newSupply.setExpirationDate(LocalDate.now());
//        newSupply.setCost(1.1);
//        newSupply.setQuantity(1.0);
//        newSupply.setMeasure(measureTest);
//        Mockito.doNothing().when(entityManager).persist(newSupply);
//        SupplyServices supplyServices = new SupplyServices();
//        supplyServices.setEntityManager(entityManager);
//        Supply result;
//        //Act        
//        try {
//            result = supplyServices.create(newSupply);
//        } catch (MandatoryAttributeSupplyException ex) {
//            result = null;
//        }
//        //Assert
//        Assert.assertEquals(result, newSupply);
//    }
//
//    @Test(expectedExceptions = MandatoryAttributeSupplyException.class,
//            expectedExceptionsMessageRegExp = "Atributo Nombre Obligatorio")
//    public void createSupplyTestThrowExceptionName() throws MandatoryAttributeSupplyException {
//        //Arrange
//        EntityManager entityManager = Mockito.mock(EntityManager.class);
//        Supply newSupply = new Supply();
//        Measure measureTest = new Measure();
//        newSupply.setExpirationDate(LocalDate.now());
//        newSupply.setCost(1.1);
//        newSupply.setQuantity(1.0);
//        newSupply.setMeasure(measureTest);
//        Mockito.doNothing().when(entityManager).persist(newSupply);
//        SupplyServices supplyServices = new SupplyServices();
//        supplyServices.setEntityManager(entityManager);
//        Supply result;
//        //Act        
//        result = supplyServices.create(newSupply);
//    }
//
//    @Test(expectedExceptions = MandatoryAttributeSupplyException.class,
//            expectedExceptionsMessageRegExp = "Atributo Fecha de Expiración Obligatorio")
//    public void createSupplyTestThrowExceptionDate() throws MandatoryAttributeSupplyException {
//        //Arrange
//        EntityManager entityManager = Mockito.mock(EntityManager.class);
//        Supply newSupply = new Supply();
//        Measure measureTest = new Measure();
//        newSupply.setName("Prueba");
//        newSupply.setCost(1.1);
//        newSupply.setQuantity(1.0);
//        newSupply.setMeasure(measureTest);
//        Mockito.doNothing().when(entityManager).persist(newSupply);
//        SupplyServices supplyServices = new SupplyServices();
//        supplyServices.setEntityManager(entityManager);
//        Supply result;
//        //Act        
//        result = supplyServices.create(newSupply);
//    }
//
//    @Test(expectedExceptions = MandatoryAttributeSupplyException.class,
//            expectedExceptionsMessageRegExp = "Atributo Costo Obligatorio")
//    public void createSupplyTestThrowExceptionCost() throws MandatoryAttributeSupplyException {
//        //Arrange
//        EntityManager entityManager = Mockito.mock(EntityManager.class);
//        Supply newSupply = new Supply();
//        Measure measureTest = new Measure();
//        newSupply.setName("Prueba");
//        newSupply.setExpirationDate(LocalDate.now());
//        newSupply.setQuantity(1.0);
//        newSupply.setMeasure(measureTest);
//        Mockito.doNothing().when(entityManager).persist(newSupply);
//        SupplyServices supplyServices = new SupplyServices();
//        supplyServices.setEntityManager(entityManager);
//        Supply result;
//        //Act        
//        result = supplyServices.create(newSupply);
//    }
//
//    @Test(expectedExceptions = MandatoryAttributeSupplyException.class,
//            expectedExceptionsMessageRegExp = "Atributo Cantidad Obligatorio")
//    public void createSupplyTestThrowExceptionQuantity() throws MandatoryAttributeSupplyException {
//        //Arrange
//        EntityManager entityManager = Mockito.mock(EntityManager.class);
//        Supply newSupply = new Supply();
//        Measure measureTest = new Measure();
//        newSupply.setName("Prueba");
//        newSupply.setExpirationDate(LocalDate.now());
//        newSupply.setCost(1.1);
//        newSupply.setMeasure(measureTest);
//        Mockito.doNothing().when(entityManager).persist(newSupply);
//        SupplyServices supplyServices = new SupplyServices();
//        supplyServices.setEntityManager(entityManager);
//        Supply result;
//        //Act        
//        result = supplyServices.create(newSupply);
//    }
//
//    @Test(expectedExceptions = MandatoryAttributeSupplyException.class,
//            expectedExceptionsMessageRegExp = "Atributo Medida Obligatorio")
//    public void createSupplyTestThrowExceptionMeasure() throws MandatoryAttributeSupplyException {
//        //Arrange
//        EntityManager entityManager = Mockito.mock(EntityManager.class);
//        Supply newSupply = new Supply();
//        Measure measureTest = new Measure();
//        newSupply.setName("Prueba");
//        newSupply.setExpirationDate(LocalDate.now());
//        newSupply.setCost(1.1);
//        newSupply.setQuantity(1.0);
//        Mockito.doNothing().when(entityManager).persist(newSupply);
//        SupplyServices supplyServices = new SupplyServices();
//        supplyServices.setEntityManager(entityManager);
//        Supply result;
//        //Act        
//        result = supplyServices.create(newSupply);
//    }
//
//    @Test
//    public void modifyByMissingTestCorrect() {
//        try {
//            //Arrange
//            ModifySupply modifySupply = new ModifySupply();
//            SupplyServices supplyServices = new SupplyServices();
//            ModifySupplyService modifySupplyService = new ModifySupplyService();
//
//            EntityManager entityManager = Mockito.mock(EntityManager.class);
//            Mockito.doNothing().when(entityManager).persist(modifySupply);
//
//            modifySupplyService.setEntityManager(entityManager);
//            supplyServices.setModifySupplyService(modifySupplyService);
//
//            Supply supplyToChangeTest = new Supply();
//            supplyToChangeTest.setQuantity(1.0);
//            String noteModifyTest = "test note";
//            Double newQuantityTest = 5.0;
//            User userTest = new User();
//
//            //Act
//            Supply result = supplyServices.modifyByMissing(supplyToChangeTest, newQuantityTest, userTest, noteModifyTest);
//
//            //Assert
////            Assert.assertEquals(result.getQuantity(), newQuantityTest);
//        } catch (MandatoryAttributeSupplyException ex) {
//            System.out.println("Error");
//        }
//    }
//
//    @Test(expectedExceptions = MandatoryAttributeSupplyException.class,
//            expectedExceptionsMessageRegExp = "Atributo Cantidad Obligatorio")
//    public void modifyByMissingTestThrowExceptionQuantity() throws MandatoryAttributeSupplyException {
//        //Arrange
//        ModifySupply modifySupply = new ModifySupply();
//        SupplyServices supplyServices = new SupplyServices();
//        ModifySupplyService modifySupplyService = new ModifySupplyService();
//
//        EntityManager entityManager = Mockito.mock(EntityManager.class);
//        Mockito.doNothing().when(entityManager).persist(modifySupply);
//
//        modifySupplyService.setEntityManager(entityManager);
//        supplyServices.setModifySupplyService(modifySupplyService);
//
//        Supply supplyToChangeTest = new Supply();
//        supplyToChangeTest.setQuantity(1.0);
//        String noteModifyTest = "test note";
//        Double newQuantityTest = null;
//        User userTest = new User();
//
//        //Act
//        Supply result = supplyServices.modifyByMissing(supplyToChangeTest, newQuantityTest, userTest, noteModifyTest);
//    }
//
//    @Test
//    public void modifyByTheftTest() {
//        //Arrange
//        ModifySupply modifySupply = new ModifySupply();
//        SupplyServices supplyServices = new SupplyServices();
//        ModifySupplyService modifySupplyService = new ModifySupplyService();
//
//        EntityManager entityManager = Mockito.mock(EntityManager.class);
//        Mockito.doNothing().when(entityManager).persist(modifySupply);
//
//        modifySupplyService.setEntityManager(entityManager);
//        supplyServices.setModifySupplyService(modifySupplyService);
//
//        Supply supplyToChangeTest = new Supply();
//        supplyToChangeTest.setQuantity(1.0);
//        String noteModifyTest = "test note";
//        User userTest = new User();
//        Double quantityExpected = 0.0;
//
//        //Act
//        Supply result = supplyServices.modifyByTheft(supplyToChangeTest, userTest, noteModifyTest);
//
//        //Assert
//        Assert.assertEquals(result.getQuantity(), quantityExpected);
//        Assert.assertEquals(result.isAvailability(), false);
//    }
//
//    @Test
//    public void deactiveSupplyTest() {
//        //Arrange
//        Supply supplyToChangeTest = new Supply();
//        supplyToChangeTest.setAvailability(true);
//        Double quantityExpected = 0.0;
//        SupplyServices supplyServices = new SupplyServices();
//
//        //Act
//        Supply result = supplyServices.deactiveSupply(supplyToChangeTest);
//
//        //Assert
//        Assert.assertEquals(result.isAvailability(), false);
//        Assert.assertEquals(result.getQuantity(), quantityExpected);
//    }
//
//    @Test
//    public void activeSupplyTestCorrect() {
//        try {
//            //Arrange
//            Supply supplyToChangeTest = new Supply();
//            supplyToChangeTest.setAvailability(false);
//            Double newQuantity = 10.0;
//            SupplyServices supplyServices = new SupplyServices();
//
//            //Act
//            Supply result = supplyServices.activateSupply(supplyToChangeTest, newQuantity);
//
//            //Assert
//            Assert.assertEquals(result.isAvailability(), true);
//            Assert.assertEquals(result.getQuantity(), newQuantity);
//        } catch (MandatoryAttributeSupplyException ex) {
//            System.out.println("Error");
//        }
//    }
//
//    @Test(expectedExceptions = MandatoryAttributeSupplyException.class,
//            expectedExceptionsMessageRegExp = "Atributo Cantidad Obligatorio")
//    public void activeSupplyTestThrowException() throws MandatoryAttributeSupplyException {
//        //Arrange
//        Supply supplyToChangeTest = new Supply();
//        supplyToChangeTest.setAvailability(false);
//        Double newQuantity = null;
//        SupplyServices supplyServices = new SupplyServices();
//
//        //Act
//        Supply result = supplyServices.activateSupply(supplyToChangeTest, newQuantity);
//
//        //Assert
//        Assert.assertEquals(result.isAvailability(), true);
//        Assert.assertEquals(result.getQuantity(), newQuantity);
//    }
//
//    @Test
//    public void modifySupplyTestCorrect() {
//        Supply supply = new Supply();
//        String testName = "Test Name";
//        LocalDate expirationDateTest = LocalDate.now();
//        Double costTest = 1.1;
//        String descriptionTest = "Test Description";
//
//        SupplyServices supplyServices = new SupplyServices();
//
//        Supply result = supplyServices.modifySupply(supply, testName, expirationDateTest, costTest, descriptionTest);
////
////        Assert.assertEquals(result, supply);
//    }
//
//    @Test(expectedExceptions = MandatoryAttributeSupplyException.class,
//            expectedExceptionsMessageRegExp = "Atributo Nombre Obligatorio")
//    public void modifySupplyTestExceptionName() throws MandatoryAttributeSupplyException {
//        Supply supply = new Supply();
//        String testName = null;
//        LocalDate expirationDateTest = LocalDate.now();
//        Double costTest = 1.1;
//        String descriptionTest = "Test Description";
//
//        SupplyServices supplyServices = new SupplyServices();
//
//        Supply result = supplyServices.modifySupply(supply, testName, expirationDateTest, costTest, descriptionTest);
//    }
//
//    @Test(expectedExceptions = MandatoryAttributeSupplyException.class,
//            expectedExceptionsMessageRegExp = "Atributo Fecha de Expiración Obligatorio")
//    public void modifySupplyTestExceptionExpirationDate() throws MandatoryAttributeSupplyException {
//        Supply supply = new Supply();
//        String testName = "Test Name";
//        LocalDate expirationDateTest = null;
//        Double costTest = 1.1;
//        String descriptionTest = "Test Description";
//
//        SupplyServices supplyServices = new SupplyServices();
//
//        Supply result = supplyServices.modifySupply(supply, testName, expirationDateTest, costTest, descriptionTest);
//    }
//
//    @Test(expectedExceptions = MandatoryAttributeSupplyException.class,
//            expectedExceptionsMessageRegExp = "Atributo Costo Obligatorio")
//    public void modifySupplyTestExceptionCost() throws MandatoryAttributeSupplyException {
//        Supply supply = new Supply();
//        String testName = "Test Name";
//        LocalDate expirationDateTest = LocalDate.now();
//        Double costTest = null;
//        String descriptionTest = "Test Description";
//
//        SupplyServices supplyServices = new SupplyServices();
//
//        Supply result = supplyServices.modifySupply(supply, testName, expirationDateTest, costTest, descriptionTest);
//    }
//
//    @Test(expectedExceptions = MandatoryAttributeSupplyException.class,
//            expectedExceptionsMessageRegExp = "Atributo Medida Obligatorio")
//    public void modifySupplyTestExceptionMeasure() throws MandatoryAttributeSupplyException {
//        Supply supply = new Supply();
//        String testName = "Test Name";
//        LocalDate expirationDateTest = LocalDate.now();
//        Double costTest = 1.1;
//        String descriptionTest = "Test Description";
//
//        SupplyServices supplyServices = new SupplyServices();
//
//        Supply result = supplyServices.modifySupply(supply, testName, expirationDateTest, costTest, descriptionTest);
//    }
//}
