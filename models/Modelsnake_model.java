
public static class Modelsnake_model extends ModelBase {
	private final ModelRenderer tail1;
	private final ModelRenderer tail2;
	private final ModelRenderer tail3;
	private final ModelRenderer tail4;
	private final ModelRenderer tail5;
	private final ModelRenderer tail6;
	private final ModelRenderer tail7;
	private final ModelRenderer tail8;
	private final ModelRenderer tail9;
	private final ModelRenderer tail10;
	private final ModelRenderer bb_main;

	public Modelsnake_model() {
		textureWidth = 32;
		textureHeight = 32;

		tail1 = new ModelRenderer(this);
		tail1.setRotationPoint(0.0F, 23.75F, -5.0F);
		tail1.cubeList.add(new ModelBox(tail1, 0, 12, -0.5F, -0.75F, 0.0F, 1, 1, 3, 0.0F, false));

		tail2 = new ModelRenderer(this);
		tail2.setRotationPoint(0.0F, 0.0F, 3.0F);
		tail1.addChild(tail2);
		tail2.cubeList.add(new ModelBox(tail2, 10, 4, -0.5F, -0.75F, 0.0F, 1, 1, 3, 0.0F, false));

		tail3 = new ModelRenderer(this);
		tail3.setRotationPoint(0.0F, 0.0F, 3.0F);
		tail2.addChild(tail3);
		tail3.cubeList.add(new ModelBox(tail3, 10, 0, -0.5F, -0.75F, 0.0F, 1, 1, 3, 0.0F, false));

		tail4 = new ModelRenderer(this);
		tail4.setRotationPoint(0.0F, 0.0F, 3.0F);
		tail3.addChild(tail4);
		tail4.cubeList.add(new ModelBox(tail4, 10, 10, -0.5F, -0.75F, 0.0F, 1, 1, 3, 0.0F, false));

		tail5 = new ModelRenderer(this);
		tail5.setRotationPoint(0.0F, 0.0F, 3.0F);
		tail4.addChild(tail5);
		tail5.cubeList.add(new ModelBox(tail5, 5, 9, -0.5F, -0.75F, 0.0F, 1, 1, 3, 0.0F, false));

		tail6 = new ModelRenderer(this);
		tail6.setRotationPoint(0.0F, 0.0F, 3.0F);
		tail5.addChild(tail6);
		tail6.cubeList.add(new ModelBox(tail6, 0, 8, -0.5F, -0.75F, 0.0F, 1, 1, 3, 0.0F, false));

		tail7 = new ModelRenderer(this);
		tail7.setRotationPoint(0.0F, 0.0F, 3.0F);
		tail6.addChild(tail7);
		tail7.cubeList.add(new ModelBox(tail7, 5, 5, -0.5F, -0.75F, 0.0F, 1, 1, 3, 0.0F, false));

		tail8 = new ModelRenderer(this);
		tail8.setRotationPoint(0.0F, -0.25F, 3.0F);
		tail7.addChild(tail8);
		tail8.cubeList.add(new ModelBox(tail8, 5, 1, -0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F, false));

		tail9 = new ModelRenderer(this);
		tail9.setRotationPoint(0.0F, 0.0F, 3.0F);
		tail8.addChild(tail9);
		tail9.cubeList.add(new ModelBox(tail9, 0, 4, -0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F, false));

		tail10 = new ModelRenderer(this);
		tail10.setRotationPoint(0.0F, 0.0F, 3.0F);
		tail9.addChild(tail10);
		tail10.cubeList.add(new ModelBox(tail10, 0, 0, -0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F, false));

		bb_main = new ModelRenderer(this);
		bb_main.setRotationPoint(0.0F, 24.0F, 0.0F);
		bb_main.cubeList.add(new ModelBox(bb_main, 5, 13, -0.5F, -1.0F, -8.0F, 1, 1, 3, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		tail1.render(f5);
		bb_main.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.tail1.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
		this.tail2.rotateAngleY = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.tail3.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
		this.bb_main.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.bb_main.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.tail4.rotateAngleY = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.tail5.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
		this.tail10.rotateAngleY = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.tail6.rotateAngleY = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.tail7.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
		this.tail8.rotateAngleY = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.tail9.rotateAngleY = MathHelper.cos(f * 0.6662F) * f1;
	}
}