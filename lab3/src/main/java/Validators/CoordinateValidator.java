package Validators;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("coordinateValidator")
public class CoordinateValidator implements Validator<String> {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, String strValue) throws ValidatorException {
        Double xLeftBorder = -5d;
        boolean xLeftBorderStrict = false;
        Double xRightBorder = 5d;
        boolean xRightBorderStrict = false;
        Double yLeftBorder = -3d;
        boolean yLeftBorderStrict = false;
        Double yRightBorder = 3d;
        boolean yRightBorderStrict = false;
        Double rLeftBorder = 1d;
        boolean rLeftBorderStrict = false;
        Double rRightBorder = 4d;
        boolean rRightBorderStrict = false;

        String id = uiComponent.getId();
        if (strValue == null || strValue.isEmpty()) {
            throw new ValidatorException(new FacesMessage(getNullMessage(id)));
        }

        String NUMBER_REGEX = "^-?\\d+(\\.\\d+)?$";
        if (!strValue.matches(NUMBER_REGEX)) {
            throw new ValidatorException(new FacesMessage("Введите число!"));
        }

        Double value = Double.parseDouble(strValue);

        switch (id) {
            case "x":
                checkBorders(value, xLeftBorder, xRightBorder, xLeftBorderStrict, xRightBorderStrict, id);
                break;
            case "y":
                checkBorders(value, yLeftBorder, yRightBorder, yLeftBorderStrict, yRightBorderStrict, id);
                break;
            case "r":
                checkBorders(value, rLeftBorder, rRightBorder, rLeftBorderStrict, rRightBorderStrict, id);
                break;
            default:
                throw new ValidatorException(new FacesMessage("How?"));
        }
    }

    private String getNullMessage(String id) {
        return id + " должен быть заполнен!";
    }

    private void checkBorders(Double value, Double leftBorder, Double rightBorder, boolean leftStrict, boolean rightStrict, String id) {
        String message = "";
        if (leftStrict && rightStrict) {
            if (!(value > leftBorder) || !(value < rightBorder)) {
                message = leftBorder + "<" + id + "<" + rightBorder;
            }
        } else if (leftStrict) {
            if (!(value > leftBorder) || !(value <= rightBorder)) {
                message = leftBorder + "<" + id + "<=" + rightBorder;
            }
        } else if (rightStrict) {
            if (!(value >= leftBorder) || !(value < rightBorder)) {
                message = leftBorder + "<=" + id + "<" + rightBorder;
            }
        } else {
            if (!(value >= leftBorder) || !(value <= rightBorder)) {
                message = leftBorder + "<=" + id + "<=" + rightBorder;
            }
        }

        if (!message.equals("")) {
            throw new ValidatorException(new FacesMessage(message));
        }
    }
}
