import chisel3._
import chisel3.stage._
import chisel3.util._
import chisel3.stage.ChiselStage

class Decoder(inputBits: Int) extends Module{
  //module port declaration
  val out_width=math.pow(2,inputBits).toInt
  val io = IO(new Bundle {
    val din = Input(UInt(inputBits.W))
    val dout = Output(UInt(out_width.W))
  })
  //module body
  io.dout:= 1.U(out_width.W) << io.din

}


object Decoder extends App {
  val verilogString = (new chisel3.stage.ChiselStage).execute(Array(""),
    Seq(ChiselGeneratorAnnotation(() => (new Decoder(3)))))

  println((new ChiselStage).emitVerilog(new Decoder(6)))

  //SoCTop是设计文件(Class)
  /*
  val targetDir      = "test_run_dir/SoCTop"
  (new ElkStage).execute(
    Array("-td", targetDir),
    Seq(ChiselGeneratorAnnotation(() => new SoCTop))  //这是生成架构图的,可以先不用
    )*/
}

