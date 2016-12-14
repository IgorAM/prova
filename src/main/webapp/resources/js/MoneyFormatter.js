var MoneyFormatter = function() {
    return {
        format: function(value)
        {
            return accounting.formatMoney(value, "R$", 2, ".", ",");
        }
    };
}();
