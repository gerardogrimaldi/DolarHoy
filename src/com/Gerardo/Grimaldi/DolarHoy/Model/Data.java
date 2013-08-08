package com.Gerardo.Grimaldi.DolarHoy.Model;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Milo
 * Date: 04/05/13
 * Time: 11:54
 * To change this template use File | Settings | File Templates.
 */
public class Data {

    private BigDecimal ValorDolarHoyCompra;
    private BigDecimal ValorDolarHoyVenta;
    private BigDecimal ValorDolarBlueCompra;
    private BigDecimal ValorDolarBlueVenta;
    private BigDecimal ValorDolarTarjeta;
    private BigDecimal ValorRealHoyVenta;
    private BigDecimal ValorRealHoyCompra;
    private BigDecimal ValorEuroHoyCompra;
    private BigDecimal ValorEuroHoyVenta;


    public Data(BigDecimal valorDolarHoyCompra,
                BigDecimal valorDolarHoyVenta,
                BigDecimal valorDolarBlueCompra,
                BigDecimal valorDolarBlueVenta,
                BigDecimal valorDolarTarjeta,
                BigDecimal valorRealHoyCompra,
                BigDecimal valorRealHoyVenta,
                BigDecimal valorEuroHoyCompra,
                BigDecimal valorEuroHoyVenta
    ) {
        super();
        this.ValorDolarHoyCompra = valorDolarHoyCompra;
        this.ValorDolarHoyVenta = valorDolarHoyVenta;
        this.ValorDolarBlueCompra = valorDolarBlueCompra;
        this.ValorDolarBlueVenta = valorDolarBlueVenta;
        this.ValorDolarTarjeta = valorDolarTarjeta;
        this.ValorEuroHoyCompra = valorEuroHoyCompra;
        this.ValorEuroHoyVenta = valorEuroHoyVenta;
        this.ValorRealHoyCompra = valorRealHoyCompra;
        this.ValorRealHoyVenta = valorRealHoyVenta;
    }

    public BigDecimal getValorDolarHoyCompra() {
        return ValorDolarHoyCompra;
    }

    public void setValorDolarHoyCompra(BigDecimal valorDolarHoyCompra) {
        ValorDolarHoyCompra = valorDolarHoyCompra;
    }

    public BigDecimal getValorDolarHoyVenta() {
        return ValorDolarHoyVenta;
    }

    public void setValorDolarHoyVenta(BigDecimal valorDolarHoyVenta) {
        ValorDolarHoyVenta = valorDolarHoyVenta;
    }

    public BigDecimal getValorDolarBlueCompra() {
        return ValorDolarBlueCompra;
    }

    public void setValorDolarBlueCompra(BigDecimal valorDolarBlueCompra) {
        ValorDolarBlueCompra = valorDolarBlueCompra;
    }

    public BigDecimal getValorDolarBlueVenta() {
        return ValorDolarBlueVenta;
    }

    public void setValorDolarBlueVenta(BigDecimal valorDolarBlueVenta) {
        ValorDolarBlueVenta = valorDolarBlueVenta;
    }

    public BigDecimal getValorDolarTarjeta() {
        return ValorDolarTarjeta;
    }

    public void setValorDolarTarjeta(BigDecimal valorDolarTarjeta) {
        ValorDolarTarjeta = valorDolarTarjeta;
    }

    public BigDecimal getValorEuroHoyCompra() {
        return ValorEuroHoyCompra;
    }

    public void setValorEuroHoyCompra(BigDecimal valorEuroHoyCompra) {
        ValorEuroHoyCompra = valorEuroHoyCompra;
    }

    public BigDecimal getValorEuroHoyVenta() {
        return ValorEuroHoyVenta;
    }

    public void setValorEuroHoyVenta(BigDecimal valorEuroHoyVenta) {
        ValorEuroHoyVenta = valorEuroHoyVenta;
    }

    public BigDecimal getValorRealHoyVenta() {
        return ValorRealHoyVenta;
    }

    public void setValorRealHoyVenta(BigDecimal valorEuroHoyVenta) {
        ValorRealHoyVenta = valorEuroHoyVenta;
    }


}

